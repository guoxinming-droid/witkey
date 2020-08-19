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
import co.zhenxi.modules.shop.domain.ZbCashout;
import co.zhenxi.modules.shop.service.ZbCashoutService;
import co.zhenxi.modules.shop.service.dto.ZbCashoutDto;
import co.zhenxi.modules.shop.service.dto.ZbCashoutQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbCashoutMapper;
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
* @author guoke
* @date 2020-08-01
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbCashout")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbCashoutServiceImpl extends BaseServiceImpl<ZbCashoutMapper, ZbCashout> implements ZbCashoutService {

    private final IGenerator generator;
    private final ZbCashoutMapper zbCashoutMapper;
    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbCashoutQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbCashout> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbCashoutDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbCashout> queryAll(ZbCashoutQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbCashout.class, criteria));
    }


    @Override
    public void download(List<ZbCashoutDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbCashoutDto zbCashout : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("提现用户ID", zbCashout.getUid());
            map.put("平台支付渠道类型", zbCashout.getPayType());
            map.put("平台支付账号", zbCashout.getPayAccount());
            map.put("平台支付渠道流水号", zbCashout.getPayCode());
            map.put("提现金额", zbCashout.getCash());
            map.put("提现手续费", zbCashout.getFees());
            map.put("用户提现真实金额", zbCashout.getRealCash());
            map.put("管理员账号", zbCashout.getAdminUid());
            map.put("提现类型 1：支付宝 2：银行卡", zbCashout.getCashoutType());
            map.put("提现账号", zbCashout.getCashoutAccount());
            map.put(" status",  zbCashout.getStatus());
            map.put("提现备注", zbCashout.getNote());
            map.put(" createdAt",  zbCashout.getCreatedAt());
            map.put(" updatedAt",  zbCashout.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> financeWithdraw(ZbCashoutQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        criteria.setStatus(1);
        PageInfo<ZbCashout> page = new PageInfo<>(financeWithdraw(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbCashoutDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbCashout> financeWithdraw(ZbCashoutQueryCriteria criteria) {
        return zbCashoutMapper.financeWithdraw(QueryHelpPlus.getPredicate(ZbCashout.class, criteria));
    }

    @Override
    public List<ZbCashout> financeProfit(String start, String end) {
        return zbCashoutMapper.financeProfit(start,end);
    }

    @Override
    public Map<String, Object> cashoutList(String cashoutType, String userName, String startTime, String endTime, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbCashout> page = new PageInfo<>(cashoutList(cashoutType,userName,startTime,endTime));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbCashoutDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbCashout> cashoutList(String cashoutType, String userName, String startTime, String endTime) {
        return zbCashoutMapper.cashoutList(cashoutType,userName,startTime,endTime);
    }

}
