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
import co.zhenxi.modules.shop.domain.ZbTaskExtra;
import co.zhenxi.modules.shop.service.ZbTaskExtraService;
import co.zhenxi.modules.shop.service.dto.ZbTaskExtraDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskExtraQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbTaskExtraMapper;
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
* @date 2020-07-22
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbTaskExtra")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbTaskExtraServiceImpl extends BaseServiceImpl<ZbTaskExtraMapper, ZbTaskExtra> implements ZbTaskExtraService {

    private final IGenerator generator;
    private final ZbTaskExtraMapper zbTaskExtraMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbTaskExtraQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTaskExtra> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskExtraDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbTaskExtra> queryAll(ZbTaskExtraQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbTaskExtra.class, criteria));
    }


    @Override
    public void download(List<ZbTaskExtraDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbTaskExtraDto zbTaskExtra : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务编号", zbTaskExtra.getTaskId());
            map.put("seo标题", zbTaskExtra.getSeoTitle());
            map.put("seo关键词", zbTaskExtra.getSeoKeyword());
            map.put("seo描述", zbTaskExtra.getSeoContent());
            map.put("创建时间", zbTaskExtra.getCreatedAt());
            map.put("修改时间", zbTaskExtra.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public ZbTaskExtra getByTaskId(long taskId) {
        return zbTaskExtraMapper.getByTaskId(taskId);
    }
}
