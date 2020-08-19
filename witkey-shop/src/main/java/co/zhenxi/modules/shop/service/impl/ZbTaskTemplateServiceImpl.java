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
import co.zhenxi.modules.shop.domain.ZbTaskTemplate;
import co.zhenxi.modules.shop.service.ZbTaskTemplateService;
import co.zhenxi.modules.shop.service.dto.ZbTaskTemplateDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskTemplateQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbTaskTemplateMapper;
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
//@CacheConfig(cacheNames = "zbTaskTemplate")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbTaskTemplateServiceImpl extends BaseServiceImpl<ZbTaskTemplateMapper, ZbTaskTemplate> implements ZbTaskTemplateService {

    private final IGenerator generator;



    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbTaskTemplateQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTaskTemplate> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskTemplateDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbTaskTemplate> queryAll(ZbTaskTemplateQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbTaskTemplate.class, criteria));
    }


    @Override
    public void download(List<ZbTaskTemplateDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbTaskTemplateDto zbTaskTemplate : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("模板标题", zbTaskTemplate.getTitle());
            map.put("模板内容", zbTaskTemplate.getContent());
            map.put("模板类型", zbTaskTemplate.getCateId());
            map.put("状态", zbTaskTemplate.getStatus());
            map.put("创建时间", zbTaskTemplate.getCreatedAt());
            map.put("修改时间", zbTaskTemplate.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
