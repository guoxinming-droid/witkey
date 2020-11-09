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
import co.zhenxi.modules.shop.domain.ZbConfig;
import co.zhenxi.modules.shop.service.ZbConfigService;
import co.zhenxi.modules.shop.service.dto.ZbConfigDto;
import co.zhenxi.modules.shop.service.dto.ZbConfigQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbConfigMapper;
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
* @date 2020-07-17
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbConfig")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbConfigServiceImpl extends BaseServiceImpl<ZbConfigMapper, ZbConfig> implements ZbConfigService {

    private final IGenerator generator;

    private final ZbConfigMapper zbConfigMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbConfigQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbConfig> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbConfigDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbConfig> queryAll(ZbConfigQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbConfig.class, criteria));
    }


    @Override
    public void download(List<ZbConfigDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbConfigDto zbConfig : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("配置别名", zbConfig.getAlias());
            map.put("配置规则", zbConfig.getRule());
            map.put("配置类型", zbConfig.getType());
            map.put("配置名称", zbConfig.getTitle());
            map.put("配置描述", zbConfig.getDes());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<ZbConfig> getZbSiteBy(String type) {
        String whereSql = "where 1 = 1";
        if(type != null && !"".equals(type)){
            whereSql += " AND type = "+type;
        }
        return zbConfigMapper.getZbSiteBy(whereSql);
    }

    @Override
    public List<ZbConfig> getZbSite() {
        String whereSql = " where 1 = 1 AND type in ('basis','site')";
        return zbConfigMapper.getZbSiteBy(whereSql);
    }
}
