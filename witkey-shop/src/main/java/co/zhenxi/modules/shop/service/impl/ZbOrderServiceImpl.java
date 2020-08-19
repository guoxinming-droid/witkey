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
import co.zhenxi.modules.shop.domain.ZbFinancial;
import co.zhenxi.modules.shop.domain.ZbOrder;
import co.zhenxi.modules.shop.domain.ZbSubOrder;
import co.zhenxi.modules.shop.service.ZbCashoutService;
import co.zhenxi.modules.shop.service.ZbOrderService;
import co.zhenxi.modules.shop.service.ZbSubOrderService;
import co.zhenxi.modules.shop.service.dto.ZbOrderDto;
import co.zhenxi.modules.shop.service.dto.ZbOrderQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbOrderMapper;
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
//@CacheConfig(cacheNames = "zbOrder")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbOrderServiceImpl extends BaseServiceImpl<ZbOrderMapper, ZbOrder> implements ZbOrderService {

    private final IGenerator generator;
    private final ZbOrderMapper zbOrderMapper;
    private final ZbSubOrderService zbSubOrderService;
    private final ZbCashoutService  zbCashoutService;
    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbOrderQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbOrder> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbOrderDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbOrder> queryAll(ZbOrderQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbOrder.class, criteria));
    }


    @Override
    public void download(List<ZbOrderDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbOrderDto zbOrder : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("订单编号", zbOrder.getCode());
            map.put("订单标题", zbOrder.getTitle());
            map.put("用户ID", zbOrder.getUid());
            map.put("任务订单的任务id", zbOrder.getTaskId());
            map.put("订单金额", zbOrder.getCash());
            map.put("订单状态: 0", zbOrder.getStatus());
            map.put("开票状态", zbOrder.getInvoiceStatus());
            map.put("订单备注", zbOrder.getNote());
            map.put("创建时间", zbOrder.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> financeProfit(String start,String end,String from,Pageable pageable) {
        getPage(pageable);
        if ("tool".equals(from)) {
            PageInfo<ZbSubOrder> page1 = new PageInfo<>(zbSubOrderService.financeProfit(start,end));
            Map<String, Object> map1 = new LinkedHashMap<>(2);
            map1.put("content", generator.convert(page1.getList(), ZbOrderDto.class));
            map1.put("totalElements", page1.getTotal());
            return map1;
        } else if ("cashout".equals(from)) {
            PageInfo<ZbCashout> page2 = new PageInfo<>(zbCashoutService.financeProfit(start,end));
            Map<String, Object> map2 = new LinkedHashMap<>(2);
            map2.put("content", generator.convert(page2.getList(), ZbOrderDto.class));
            map2.put("totalElements", page2.getTotal());
            return map2;
        }

        PageInfo<ZbOrder> page = new PageInfo<>(financeProfit1(start,end));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbOrderDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbOrder> financeProfit1(String start, String end) {
        return zbOrderMapper.financeProfit(start,end);
    }

    @Override
    public List<ZbOrder> financeStatement(String startTime, String endTime) {
        return zbOrderMapper.financeStatement(startTime,endTime);
    }

    @Override
    public ZbOrder getByCode(String code) {
        return zbOrderMapper.getByCode(code);
    }
}
