/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbLink;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbLinkService;
import co.zhenxi.modules.shop.service.dto.ZbLinkDto;
import co.zhenxi.modules.shop.service.dto.ZbLinkQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbLinkMapper;
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
//@CacheConfig(cacheNames = "zbLink")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbLinkServiceImpl extends BaseServiceImpl<ZbLinkMapper, ZbLink> implements ZbLinkService {

    private final IGenerator generator;

    private final ZbLinkMapper zbLinkMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbLinkQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbLink> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbLinkDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbLink> queryAll(ZbLinkQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbLink.class, criteria));
    }


    @Override
    public void download(List<ZbLinkDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbLinkDto zbLink : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("标题", zbLink.getTitle());
            map.put("链接名称", zbLink.getContent());
            map.put("添加时间", zbLink.getAddtime());
            map.put("状态", zbLink.getStatus());
            map.put("排序", zbLink.getSort());
            map.put(" pic",  zbLink.getPic());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 获取友情链接
     *
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> getLink(Pageable size) {
        getPage(size);
        Page<ZbLink> page = zbLinkMapper.getLink();
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getResult(), ZbLink.class));
        map.put("totalElements", page.getTotal());
        return map;
    }
}
