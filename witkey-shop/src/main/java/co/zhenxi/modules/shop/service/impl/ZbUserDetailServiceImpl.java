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
import co.zhenxi.modules.shop.domain.ZbUserDetail;
import co.zhenxi.modules.shop.domain.ZbUserDetailAdvice;
import co.zhenxi.modules.shop.service.ZbCateService;
import co.zhenxi.modules.shop.service.ZbUserDetailService;
import co.zhenxi.modules.shop.service.dto.ZbUserDetailDto;
import co.zhenxi.modules.shop.service.dto.ZbUserDetailQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbCateMapper;
import co.zhenxi.modules.shop.service.mapper.ZbUserDetailMapper;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
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
* @date 2020-07-27
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbUserDetail")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbUserDetailServiceImpl extends BaseServiceImpl<ZbUserDetailMapper, ZbUserDetail> implements ZbUserDetailService {

    private final IGenerator generator;
    private final ZbUserDetailMapper zbUserDetailMapper;
    private final  ZbCateService zbCateService;
    private final ZbShopServiceImpl zbShopServiceImpl;




    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbUserDetailQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbUserDetail> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbUserDetailDto.class));
        map.put("pageNum",page.getPageNum());
        map.put("pages",page.getPages());
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbUserDetail> queryAll(ZbUserDetailQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbUserDetail.class, criteria));
    }


    @Override
    public void download(List<ZbUserDetailDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbUserDetailDto zbUserDetail : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户编号", zbUserDetail.getUid());
            map.put("真实姓名", zbUserDetail.getRealname());
            map.put("表示真实姓名是否公开 0表示不公开 1表示公开", zbUserDetail.getRealnameStatus());
            map.put("0表示女 1表示男", zbUserDetail.getSex());
            map.put("手机号码", zbUserDetail.getMobile());
            map.put("0表示不公开 1表示公开", zbUserDetail.getMobileStatus());
            map.put("app端用户昵称", zbUserDetail.getNickname());
            map.put("用户qq", zbUserDetail.getQq());
            map.put("qq是否公开 0表示不公开 1表示公开", zbUserDetail.getQqStatus());
            map.put("用户微信号", zbUserDetail.getWechat());
            map.put("qq是否公开 0表示不公开 1表示公开", zbUserDetail.getWechatStatus());
            map.put("身份证号码", zbUserDetail.getCardNumber());
            map.put("用户省份", zbUserDetail.getProvince());
            map.put("用户城市", zbUserDetail.getCity());
            map.put("用户地区", zbUserDetail.getArea());
            map.put("用户头像", zbUserDetail.getAvatar());
            map.put("个人签名", zbUserDetail.getAutograph());
            map.put("个人简介", zbUserDetail.getIntroduce());
            map.put("个人标签", zbUserDetail.getSign());
            map.put("做为服务商的好评数量", zbUserDetail.getEmployeePraiseRate());
            map.put("做为雇主的好评数量", zbUserDetail.getEmployerPraiseRate());
            map.put("承接任务数量", zbUserDetail.getReceiveTaskNum());
            map.put("发布任务数量", zbUserDetail.getPublishTaskNum());
            map.put("店铺状态: -1.管理员禁用店铺 0.未开启店铺 1.开启店铺 2.关闭店铺", zbUserDetail.getShopStatus());
            map.put("用户资产余额", zbUserDetail.getBalance());
            map.put("资产冻结 0表示未冻结 1表示资金被冻结", zbUserDetail.getBalanceStatus());
            map.put("最后一次登录时间", zbUserDetail.getLastLoginTime());
            map.put("空间个人资料背景图片", zbUserDetail.getBackgroundurl());
            map.put("支付提示 0：提示 1：不提示", zbUserDetail.getAlternateTips());
            map.put("累计雇佣量", zbUserDetail.getEmployeeNum());
            map.put(" createdAt",  zbUserDetail.getCreatedAt());
            map.put(" updatedAt",  zbUserDetail.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public boolean updateUserDetail(Integer uid , BigDecimal balances) {
        return zbUserDetailMapper.updateUserDetail(uid,balances);
    }

    /**
     * 条件查询服务商
     *
     * @param zbUserDetailQueryCriteria
     * @param cateId
     * @param pageable
     * @return
     */
    @Override
    public Map<String, Object> queryAllByCateId(ZbUserDetailQueryCriteria zbUserDetailQueryCriteria, Integer cateId, Pageable pageable) {


       String whereSql = "";
       if(cateId!=null && cateId >0){
           if(isCatePid(cateId)) {
               whereSql += " and zb_shop.status = 1 and zb_shop.cate_pid =zb_cate.id and zb_shop.cate_pid =" + cateId;
           }else {
               whereSql += " and zb_shop.status = 1 and zb_shop.cate_pid =zb_cate.id and zb_shop.cate_sid =" + cateId;
           }
       }
       if(zbUserDetailQueryCriteria.getProvince()!=null && zbUserDetailQueryCriteria.getProvince()>0){
           whereSql +=" and zb_shop.cate_sid = zb_cate.id  and zb_user_detail.province ="+zbUserDetailQueryCriteria.getProvince();
       }
        if(zbUserDetailQueryCriteria.getCity()!=null && zbUserDetailQueryCriteria.getCity()>0){
            whereSql +="and zb_shop.cate_sid = zb_cate.id and zb_user_detail.city ="+zbUserDetailQueryCriteria.getCity();
        }
        if(zbUserDetailQueryCriteria.getArea()!=null && zbUserDetailQueryCriteria.getArea()>0){
            whereSql +="and zb_shop.cate_sid = zb_cate.id and zb_user_detail.area ="+zbUserDetailQueryCriteria.getArea();
        }
        if(zbUserDetailQueryCriteria.getName()!=null && !"".equals(zbUserDetailQueryCriteria.getName())){
            System.out.println(zbUserDetailQueryCriteria.getName());
            whereSql += "and zb_shop.cate_sid = zb_cate.id and zb_users.name like '%"+zbUserDetailQueryCriteria.getName()+"%'";
            System.out.println(whereSql);
        }
        getPage(pageable);
        Page<ZbUserDetailAdvice> zbUserDetailAdvices = zbUserDetailMapper.selectAll(whereSql);
        for (ZbUserDetailAdvice zbUserDetailAdvice : zbUserDetailAdvices) {
            Map<String, Object> authentication = zbShopServiceImpl.getAuthentication(zbUserDetailAdvice.getUid());
            zbUserDetailAdvice.setAuthenticationList(authentication);
        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(zbUserDetailAdvices.getResult(), ZbUserDetailAdvice.class));
        map.put("totalElements", zbUserDetailAdvices.getTotal());
        map.put("pageNum",zbUserDetailAdvices.getPageNum());
        map.put("pages",zbUserDetailAdvices.getPages());
        return map;
    }

    @Override
    public Map<String, Object> getServiceProviderById(Integer uid) {

        return zbUserDetailMapper.getServiceProviderById(uid);
    }

    private boolean isCatePid(Integer catePid){
        List<Integer> zbCatePidList = zbCateService.getByFida();
        for (Integer integer : zbCatePidList) {
            if(catePid.equals(integer)){
                System.out.println(catePid+": "+integer);
                return true;

            }
        }
        return false;
    }
}
