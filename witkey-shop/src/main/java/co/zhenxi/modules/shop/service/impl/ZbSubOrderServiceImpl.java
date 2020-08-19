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
import co.zhenxi.modules.shop.domain.ZbSubOrder;
import co.zhenxi.modules.shop.service.ZbSubOrderService;
import co.zhenxi.modules.shop.service.dto.ZbSubOrderDto;
import co.zhenxi.modules.shop.service.dto.ZbSubOrderQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbSubOrderMapper;
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
//@CacheConfig(cacheNames = "zbSubOrder")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbSubOrderServiceImpl extends BaseServiceImpl<ZbSubOrderMapper, ZbSubOrder> implements ZbSubOrderService {

    private final IGenerator generator;
    private final  ZbSubOrderMapper zbSubOrderMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbSubOrderQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbSubOrder> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbSubOrderDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbSubOrder> queryAll(ZbSubOrderQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbSubOrder.class, criteria));
    }


    @Override
    public void download(List<ZbSubOrderDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbSubOrderDto zbSubOrder : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("子订单标题", zbSubOrder.getTitle());
            map.put("子订单备注", zbSubOrder.getNote());
            map.put("子订单金额", zbSubOrder.getCash());
            map.put("创建用户ID", zbSubOrder.getUid());
            map.put("父订单ID", zbSubOrder.getOrderId());
            map.put("父订单编号", zbSubOrder.getOrderCode());
            map.put("对象ID(TASK,SERVICE,TOOL)", zbSubOrder.getProductId());
            map.put("对象类型:1:TASK,2:SERVICE,3:TOOL", zbSubOrder.getProductType());
            map.put("子订单状态", zbSubOrder.getStatus());
            map.put("创建时间", zbSubOrder.getCreatedAt());
            map.put("修改时间", zbSubOrder.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<ZbSubOrder> financeProfit(String start, String end) {
        return zbSubOrderMapper.financeProfit(start,end);
    }

    @Override
    public List<ZbSubOrder> financeStatement(String startTime, String endTime) {
        return zbSubOrderMapper.financeStatement(startTime,endTime);
    }
}
