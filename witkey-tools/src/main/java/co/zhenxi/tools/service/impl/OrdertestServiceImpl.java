/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.tools.domain.Ordertest;
import co.zhenxi.tools.service.OrdertestService;
import co.zhenxi.tools.service.dto.OrdertestDto;
import co.zhenxi.tools.service.dto.OrdertestQueryCriteria;
import co.zhenxi.tools.service.mapper.OrdertestMapper;
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
* @author gxm
* @date 2020-07-18
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "ordertest")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrdertestServiceImpl extends BaseServiceImpl<OrdertestMapper, Ordertest> implements OrdertestService {

    private final IGenerator generator;
    private final OrdertestMapper ordertestMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(OrdertestQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<Ordertest> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), OrdertestDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<Ordertest> queryAll(OrdertestQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(Ordertest.class, criteria));
    }


    @Override
    public void download(List<OrdertestDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OrdertestDto ordertest : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("订单编号", ordertest.getCode());
            map.put("订单标题", ordertest.getTitle());
            map.put("用户ID", ordertest.getUid());
            map.put("任务订单的任务id", ordertest.getTaskId());
            map.put("订单金额", ordertest.getCash());
            map.put("订单状态: 0", ordertest.getStatus());
            map.put("开票状态", ordertest.getInvoiceStatus());
            map.put("订单备注", ordertest.getNote());
            map.put("创建时间", ordertest.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Ordertest selectByOrderId(String orderId) {
        return ordertestMapper.selectByOrderId(orderId);
    }
}
