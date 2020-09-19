/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.Trade;
import co.zhenxi.modules.shop.domain.ZbVipshopOrder;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.modules.shop.service.TradeService;
import co.zhenxi.modules.shop.service.ZbShopPackageService;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.ValidationUtil;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbVipshopOrderService;
import co.zhenxi.modules.shop.service.dto.ZbVipshopOrderDto;
import co.zhenxi.modules.shop.service.dto.ZbVipshopOrderQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbVipshopOrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author guoke
* @date 2020-07-27
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbVipshopOrder")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbVipshopOrderServiceImpl extends BaseServiceImpl<ZbVipshopOrderMapper, ZbVipshopOrder> implements ZbVipshopOrderService {

    private final IGenerator generator;

    private final ZbVipshopOrderMapper zbVipshopOrderMapper;

    private final TradeService tradeService;
    private final ZbShopPackageService zbShopPackageService;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbVipshopOrderQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbVipshopOrder> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbVipshopOrderDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbVipshopOrder> queryAll(ZbVipshopOrderQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbVipshopOrder.class, criteria));
    }


    @Override
    public void download(List<ZbVipshopOrderDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbVipshopOrderDto zbVipshopOrder : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("订单编号", zbVipshopOrder.getCode());
            map.put("订单标题", zbVipshopOrder.getTitle());
            map.put("用户名", zbVipshopOrder.getUid());
            map.put("套餐", zbVipshopOrder.getPackageId());
            map.put("店铺", zbVipshopOrder.getShopId());
            map.put("套餐时长(月)", zbVipshopOrder.getTimePeriod());
            map.put("金额", zbVipshopOrder.getCash());
            map.put("订单状态", zbVipshopOrder.getStatus());
            map.put("创建时间", zbVipshopOrder.getCreatedAt());
            map.put("修改时间", zbVipshopOrder.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 看此用户是否支付成功
     *
     * @param uid
     * @return
     */
    @Override
    public ZbVipshopOrder getByUId(Integer uid) {

        return  zbVipshopOrderMapper.getByUid(uid);
    }

    @Override
    public ZbVipshopOrder getByCode(String code) {

        return  zbVipshopOrderMapper.getByCode(code);
    }

    /**
     * 生成订单
     *
     * @param zbVipshopOrder
     * @return 订单号
     */
    @Override
    public String generateOrder(ZbVipshopOrder zbVipshopOrder) {
        zbVipshopOrderMapper.insert(zbVipshopOrder);
        ZbVipshopOrder zbVipshopOrder1 = zbVipshopOrderMapper.selectById(zbVipshopOrder.getId());
        return zbVipshopOrder1.getCode();
    }

    /**
     * 修改订单状态
     *
     * @param tradeNo
     * @return
     */
    @Override
    public HashMap<String, String> updateByCode(String tradeNo) throws Exception {
        Trade trade = tradeService.getByTradeNo(tradeNo);
        HashMap<String, String> stringStringHashMap = new HashMap<>(1);
        if(trade!=null && "1".equals(trade.getStatus())){
            zbVipshopOrderMapper.updateByTradeNo(tradeNo);
            ZbVipshopOrder byCode = zbVipshopOrderMapper.getByCode(tradeNo);
            zbShopPackageService.insert(byCode.getPackageId(),byCode.getUid(),byCode.getTimePeriod());
           stringStringHashMap.put("message","开通成功");
            return stringStringHashMap;
        }
        stringStringHashMap.put("message","开通失败，请检查订单状态");
        return stringStringHashMap;
    }
}
