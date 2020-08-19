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
import co.zhenxi.modules.shop.domain.ZbShopOrder;
import co.zhenxi.modules.shop.service.ZbShopOrderService;
import co.zhenxi.modules.shop.service.ZbUsersService;
import co.zhenxi.modules.shop.service.dto.ZbShopOrderDto;
import co.zhenxi.modules.shop.service.dto.ZbShopOrderQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbShopOrderMapper;
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
* @date 2020-07-30
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbShopOrder")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbShopOrderServiceImpl extends BaseServiceImpl<ZbShopOrderMapper, ZbShopOrder> implements ZbShopOrderService {

    private final IGenerator generator;
    private final ZbUsersService zbUsersService;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbShopOrderQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbShopOrder> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbShopOrderDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbShopOrder> queryAll(ZbShopOrderQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbShopOrder.class, criteria));
    }


    @Override
    public void download(List<ZbShopOrderDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbShopOrderDto zbShopOrder : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("订单号", zbShopOrder.getCode());
            map.put("订单标题", zbShopOrder.getTitle());
            map.put("用户编号", zbShopOrder.getUid());
            map.put("对象编号", zbShopOrder.getObjectId());
            map.put("对象类型", zbShopOrder.getObjectType());
            map.put(" cash",  zbShopOrder.getCash());
            map.put("订单状态", zbShopOrder.getStatus());
            map.put(" invoiceStatus",  zbShopOrder.getInvoiceStatus());
            map.put(" note",  zbShopOrder.getNote());
            map.put("创建订单时间", zbShopOrder.getCreatedAt());
            map.put("支付时间", zbShopOrder.getPayTime());
            map.put("交易提成比例", zbShopOrder.getTradeRate());
            map.put("确认文件时间", zbShopOrder.getConfirmTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> shopOrderList(ZbShopOrderQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        criteria.setObjectType(1);
        PageInfo<ZbShopOrder> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbShopOrderDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public ZbShopOrder findById(long id) {
        ZbShopOrder zbShopOrder = baseMapper.selectById(id);
        if(zbShopOrder != null && zbShopOrder.getId().equals(id)) {
            zbShopOrder.setZbUsers(zbUsersService.getById(zbShopOrder.getUid()));
        }
        return zbShopOrder;
    }
}
