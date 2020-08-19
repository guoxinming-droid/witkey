/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbTagShop;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.ValidationUtil;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbTagShopService;
import co.zhenxi.modules.shop.service.dto.ZbTagShopDto;
import co.zhenxi.modules.shop.service.dto.ZbTagShopQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbTagShopMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author guoke
* @date 2020-08-15
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbTagShop")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbTagShopServiceImpl extends BaseServiceImpl<ZbTagShopMapper, ZbTagShop> implements ZbTagShopService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbTagShopQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTagShop> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTagShopDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbTagShop> queryAll(ZbTagShopQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbTagShop.class, criteria));
    }


    @Override
    public void download(List<ZbTagShopDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbTagShopDto zbTagShop : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("kppw_skill_tags表的主键id", zbTagShop.getTagId());
            map.put("kppw_shop表的主键id即店铺id", zbTagShop.getShopId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
