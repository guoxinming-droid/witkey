/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbAd;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbAdService;
import co.zhenxi.modules.shop.service.dto.ZbAdDto;
import co.zhenxi.modules.shop.service.dto.ZbAdQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbAdMapper;
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
* @author Guoxm
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbAd")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbAdServiceImpl extends BaseServiceImpl<ZbAdMapper, ZbAd> implements ZbAdService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbAdQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbAd> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbAdDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbAd> queryAll(ZbAdQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbAd.class, criteria));
    }


    @Override
    public void download(List<ZbAdDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbAdDto zbAd : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("广告位", zbAd.getTargetId());
            map.put("广告类型", zbAd.getAdType());
            map.put("位置", zbAd.getAdPosition());
            map.put("广告名称", zbAd.getAdName());
            map.put("广告文件", zbAd.getAdFile());
            map.put("广告内容", zbAd.getAdContent());
            map.put("广告url", zbAd.getAdUrl());
            map.put("开始时间", zbAd.getStartTime());
            map.put("结束时间", zbAd.getEndTime());
            map.put("用户编号", zbAd.getUid());
            map.put("用户名", zbAd.getUsername());
            map.put("排序", zbAd.getListorder());
            map.put("广告状态", zbAd.getIsOpen());
            map.put("创建时间", zbAd.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
