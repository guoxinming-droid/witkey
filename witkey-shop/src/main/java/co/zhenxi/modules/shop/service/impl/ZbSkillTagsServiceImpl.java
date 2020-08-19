/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbSkillTags;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.ValidationUtil;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbSkillTagsService;
import co.zhenxi.modules.shop.service.dto.ZbSkillTagsDto;
import co.zhenxi.modules.shop.service.dto.ZbSkillTagsQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbSkillTagsMapper;
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
//@CacheConfig(cacheNames = "zbSkillTags")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbSkillTagsServiceImpl extends BaseServiceImpl<ZbSkillTagsMapper, ZbSkillTags> implements ZbSkillTagsService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbSkillTagsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbSkillTags> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbSkillTagsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbSkillTags> queryAll(ZbSkillTagsQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbSkillTags.class, criteria));
    }


    @Override
    public void download(List<ZbSkillTagsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbSkillTagsDto zbSkillTags : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("技能标签", zbSkillTags.getTagName());
            map.put("关联的任务类型", zbSkillTags.getCateId());
            map.put(" createdAt",  zbSkillTags.getCreatedAt());
            map.put(" updatedAt",  zbSkillTags.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
