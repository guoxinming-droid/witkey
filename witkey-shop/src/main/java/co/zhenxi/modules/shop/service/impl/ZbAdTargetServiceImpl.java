/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbAdTarget;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbAdTargetService;
import co.zhenxi.modules.shop.service.dto.ZbAdTargetDto;
import co.zhenxi.modules.shop.service.dto.ZbAdTargetQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbAdTargetMapper;
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
//@CacheConfig(cacheNames = "zbAdTarget")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbAdTargetServiceImpl extends BaseServiceImpl<ZbAdTargetMapper, ZbAdTarget> implements ZbAdTargetService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbAdTargetQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbAdTarget> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbAdTargetDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbAdTarget> queryAll(ZbAdTargetQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbAdTarget.class, criteria));
    }


    @Override
    public void download(List<ZbAdTargetDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbAdTargetDto zbAdTarget : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("广告位名称", zbAdTarget.getName());
            map.put("广告位标签", zbAdTarget.getCode());
            map.put("描述", zbAdTarget.getDescription());
            map.put("广告标签", zbAdTarget.getTargets());
            map.put("广告位置", zbAdTarget.getPosition());
            map.put("广告位大小", zbAdTarget.getAdSize());
            map.put("广告位个数", zbAdTarget.getAdNum());
            map.put("广告位图片", zbAdTarget.getPic());
            map.put("是否开启", zbAdTarget.getIsOpen());
            map.put("内容", zbAdTarget.getContent());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
