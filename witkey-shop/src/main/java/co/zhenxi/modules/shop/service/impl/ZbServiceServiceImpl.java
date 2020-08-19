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
import co.zhenxi.modules.shop.domain.ZbService;
import co.zhenxi.modules.shop.service.ZbServiceService;
import co.zhenxi.modules.shop.service.dto.ZbServiceDto;
import co.zhenxi.modules.shop.service.dto.ZbServiceQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbServiceMapper;
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
* @date 2020-07-28
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbServiceServiceImpl extends BaseServiceImpl<ZbServiceMapper, ZbService> implements ZbServiceService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbServiceQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbService> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbServiceDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbService> queryAll(ZbServiceQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbService.class, criteria));
    }


    @Override
    public void download(List<ZbServiceDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbServiceDto zbService : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("标题", zbService.getTitle());
            map.put("描述", zbService.getDescription());
            map.put("价格", zbService.getPrice());
            map.put("类型", zbService.getType());
            map.put("唯一标识", zbService.getIdentify());
            map.put("状态", zbService.getStatus());
            map.put("创建时间", zbService.getCreatedAt());
            map.put(" updatedAt",  zbService.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> serviceList(ZbServiceQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbService> page = new PageInfo<>(serviceList(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbServiceDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbService> serviceList(ZbServiceQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbService.class, criteria));
    }
}
