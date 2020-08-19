/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.modules.shop.domain.ZbFinancial;
import co.zhenxi.modules.shop.service.ZbFinancialService;
import co.zhenxi.modules.shop.service.dto.ZbFinancialDto;
import co.zhenxi.modules.shop.service.dto.ZbFinancialQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbFinancialMapper;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbFinancial")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbFinancialServiceImpl extends BaseServiceImpl<ZbFinancialMapper, ZbFinancial> implements ZbFinancialService {

    private final IGenerator generator;
    private final ZbFinancialMapper zbFinancialMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbFinancialQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbFinancial> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbFinancialDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> financeRecharge(ZbFinancialQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        criteria.setAction(3);
        criteria.setPayType(1);
        PageInfo<ZbFinancial> page = new PageInfo<>(financeRecharge(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbFinancialDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbFinancial> queryAll(ZbFinancialQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbFinancial.class, criteria));
    }

    @Override
    //@Cacheable
    public List<ZbFinancial> financeRecharge(ZbFinancialQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbFinancial.class, criteria));
    }

    @Override
    public void download(List<ZbFinancialDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbFinancialDto zbFinancial : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("收支行为", zbFinancial.getAction());
            map.put("支付渠道类型", zbFinancial.getPayType());
            map.put("支付账号", zbFinancial.getPayAccount());
            map.put("流水号", zbFinancial.getPayCode());
            map.put("金额", zbFinancial.getCash());
            map.put("用户", zbFinancial.getUid());
            map.put("时间", zbFinancial.getCreatedAt());
            map.put("修改时间", zbFinancial.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<ZbFinancial> financeStatement(String startTime, String endTime) {
        return zbFinancialMapper.financeStatement(startTime,endTime);
    }

    @Override
    public Map<String, Object> rechargeList(String code ,String userName,String startTime,String endTime, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbFinancial> page = new PageInfo<>(rechargeList(code,userName,startTime,endTime));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbFinancialDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbFinancial> rechargeList(String code ,String userName,String startTime,String endTime) {
        return zbFinancialMapper.rechargeList(code,userName,startTime,endTime);
    }

    @Override
    public Map<String, Object> financeList( Integer actions,String startTime,String endTime,Pageable pageable) {
        getPage(pageable);
        String whereSql =" WHERE 1 = 1 ";
        if(startTime != null && !"".equals(startTime) ){
            whereSql += " AND created_at >= "+startTime;
        }else{
            whereSql += "";
        }
        if(endTime != null && !"".equals(endTime) ){
            whereSql += " AND created_at <= "+endTime;
        }else{
            whereSql += "";
        }
        if(actions != null && actions.equals(1)){
            //收入
            whereSql += " AND action in ('2','3','7','8','9','10','11')";
            PageInfo<ZbFinancial> page = new PageInfo<>(financeList(whereSql));
            Map<String, Object> map = new LinkedHashMap<>(2);
            map.put("content", generator.convert(page.getList(), ZbFinancialDto.class));
            map.put("totalElements", page.getTotal());
            return map;
        }else if(actions != null && actions.equals(2)){
            //支出

            whereSql = " AND action in ('1','4','5','6')";
            PageInfo<ZbFinancial> page = new PageInfo<>(financeList(whereSql));
            Map<String, Object> map = new LinkedHashMap<>(2);
            map.put("content", generator.convert(page.getList(), ZbFinancialDto.class));
            map.put("totalElements", page.getTotal());
            return map;
        }
        whereSql += "";
        PageInfo<ZbFinancial> page = new PageInfo<>(financeList(whereSql));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbFinancialDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbFinancial> financeList(String whereSql) {


        return zbFinancialMapper.financeList(whereSql);
    }

    @Override
    public Map<String, Object> userFinance(String userName, Integer action, String startTime, String endTime, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbFinancial> page = new PageInfo<>(userFinance(userName,action,startTime,endTime));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbFinancialDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbFinancial> userFinance(String userName, Integer action, String startTime, String endTime) {
        String whereSql =" WHERE 1 = 1 ";
        if(userName != null && !"".equals(userName) ){
            whereSql += " AND u.name = "+userName;
        }else{
            whereSql += "";
        }

        if(action != null && !"".equals(action) ){
            whereSql += " AND f.action = "+action;
        }else{
            whereSql += "";
        }

        if(startTime != null && !"".equals(startTime) ){
            whereSql += " AND f.created_at >= "+startTime;
        }else{
            whereSql += "";
        }
        if(endTime != null && !"".equals(endTime) ){
            whereSql += " AND f.created_at <= "+endTime;
        }else{
            whereSql += "";
        }
        return zbFinancialMapper.userFinance(whereSql);
    }


}
