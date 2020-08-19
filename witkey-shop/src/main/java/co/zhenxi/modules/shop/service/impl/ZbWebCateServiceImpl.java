/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbWebCate;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbWebCateService;
import co.zhenxi.modules.shop.service.dto.ZbWebCateDto;
import co.zhenxi.modules.shop.service.dto.ZbWebCateQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbWebCateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author Guo xinming
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbWebCate")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbWebCateServiceImpl extends BaseServiceImpl<ZbWebCateMapper, ZbWebCate> implements ZbWebCateService {

    private final IGenerator generator;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbWebCateQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbWebCate> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbWebCateDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbWebCate> queryAll(ZbWebCateQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbWebCate.class, criteria));
    }


    @Override
    public void download(List<ZbWebCateDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbWebCateDto zbWebCate : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("分类名称", zbWebCate.getName());
            map.put("排序", zbWebCate.getSort());
            map.put("创建时间", zbWebCate.getCreatedAt());
            map.put("修改时间", zbWebCate.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
