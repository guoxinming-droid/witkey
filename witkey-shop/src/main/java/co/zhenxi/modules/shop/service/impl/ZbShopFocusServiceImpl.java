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
import co.zhenxi.modules.shop.domain.ZbShopFocus;
import co.zhenxi.modules.shop.service.ZbShopFocusService;
import co.zhenxi.modules.shop.service.dto.ZbShopFocusDto;
import co.zhenxi.modules.shop.service.dto.ZbShopFocusQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbShopFocusMapper;
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
* @date 2020-08-19
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbShopFocus")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbShopFocusServiceImpl extends BaseServiceImpl<ZbShopFocusMapper, ZbShopFocus> implements ZbShopFocusService {

    private final IGenerator generator;
    private final ZbShopFocusMapper zbShopFocusMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbShopFocusQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbShopFocus> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbShopFocusDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbShopFocus> queryAll(ZbShopFocusQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbShopFocus.class, criteria));
    }


    @Override
    public void download(List<ZbShopFocusDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbShopFocusDto zbShopFocus : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("关注者id", zbShopFocus.getUid());
            map.put("店铺id", zbShopFocus.getShopId());
            map.put(" createdAt",  zbShopFocus.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Boolean removeByUid(Integer uid, Integer shopId) {
        return zbShopFocusMapper.removeByUid(uid,shopId);
    }
}
