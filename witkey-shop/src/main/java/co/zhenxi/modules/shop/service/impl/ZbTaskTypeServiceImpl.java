/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbTaskType;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbTaskTypeService;
import co.zhenxi.modules.shop.service.dto.ZbTaskTypeDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskTypeQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbTaskTypeMapper;
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
* @date 2020-07-22
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbTaskType")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbTaskTypeServiceImpl extends BaseServiceImpl<ZbTaskTypeMapper, ZbTaskType> implements ZbTaskTypeService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbTaskTypeQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTaskType> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskTypeDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbTaskType> queryAll(ZbTaskTypeQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbTaskType.class, criteria));
    }


    @Override
    public void download(List<ZbTaskTypeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbTaskTypeDto zbTaskType : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("类型名称", zbTaskType.getName());
            map.put("状态", zbTaskType.getStatus());
            map.put("别名", zbTaskType.getAlias());
            map.put("创建时间", zbTaskType.getCreatedAt());
            map.put("任务类型描述", zbTaskType.getDes());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
