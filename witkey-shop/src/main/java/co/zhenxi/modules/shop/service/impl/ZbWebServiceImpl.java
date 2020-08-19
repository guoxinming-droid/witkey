/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbWeb;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbWebService;
import co.zhenxi.modules.shop.service.dto.ZbWebDto;
import co.zhenxi.modules.shop.service.dto.ZbWebQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbWebMapper;
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
//@CacheConfig(cacheNames = "zbWeb")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbWebServiceImpl extends BaseServiceImpl<ZbWebMapper, ZbWeb> implements ZbWebService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbWebQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbWeb> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbWebDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbWeb> queryAll(ZbWebQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbWeb.class, criteria));
    }


    @Override
    public void download(List<ZbWebDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbWebDto zbWeb : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("名称", zbWeb.getName());
            map.put("活动链接", zbWeb.getUrl());
            map.put("排序", zbWeb.getSort());
            map.put("栏目分类", zbWeb.getWebCateId());
            map.put("状态", zbWeb.getStatus());
            map.put("创建时间", zbWeb.getCreatedAt());
            map.put("修改时间", zbWeb.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
