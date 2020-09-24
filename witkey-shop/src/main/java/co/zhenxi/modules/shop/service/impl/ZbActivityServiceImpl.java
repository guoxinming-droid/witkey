/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbActivity;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.ValidationUtil;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbActivityService;
import co.zhenxi.modules.shop.service.dto.ZbActivityDto;
import co.zhenxi.modules.shop.service.dto.ZbActivityQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbActivityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author guoke
* @date 2020-07-31
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbActivity")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbActivityServiceImpl extends BaseServiceImpl<ZbActivityMapper, ZbActivity> implements ZbActivityService {

    private final IGenerator generator;

    private final ZbActivityMapper zbActivityMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbActivityQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbActivity> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbActivityDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbActivity> queryAll(ZbActivityQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbActivity.class, criteria));
    }


    @Override
    public void download(List<ZbActivityDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbActivityDto zbActivity : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("活动标题", zbActivity.getTitle());
            map.put("活动封面", zbActivity.getPic());
            map.put("活动链接", zbActivity.getUrl());
            map.put("排序", zbActivity.getSort());
            map.put("状态 0：未发布 1：已发布", zbActivity.getStatus());
            map.put("发布时间", zbActivity.getPubAt());
            map.put("简介", zbActivity.getDes());
            map.put(" createdAt",  zbActivity.getCreatedAt());
            map.put(" updatedAt",  zbActivity.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * @param size 每页条数
     * @return
     */
    @Override
    public Map<String, Object> getActivity(Pageable size) {
        getPage(size);
        HashMap<String, Object> map = new HashMap<>();
        Page<ZbActivity> page = zbActivityMapper.getActivity();
        map.put("content", generator.convert(page.getResult(), ZbActivityDto.class));
        map.put("pageNum",page.getPageNum());
        map.put("pages",page.getPages());
        map.put("totalElements", page.getTotal());
        return map;
    }
}
