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
import co.zhenxi.modules.shop.domain.ZbComments;
import co.zhenxi.modules.shop.service.ZbCommentsService;
import co.zhenxi.modules.shop.service.dto.ZbCommentsDto;
import co.zhenxi.modules.shop.service.dto.ZbCommentsQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbCommentsMapper;
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
* @date 2020-07-23
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbComments")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbCommentsServiceImpl extends BaseServiceImpl<ZbCommentsMapper, ZbComments> implements ZbCommentsService {

    private final IGenerator generator;
    private final ZbCommentsMapper  zbCommentsMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbCommentsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbComments> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbCommentsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbComments> queryAll(ZbCommentsQueryCriteria criteria){
        return zbCommentsMapper.selectList1(QueryHelpPlus.getPredicate(ZbComments.class, criteria));
       // return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbComments.class, criteria));
    }


    @Override
    public void download(List<ZbCommentsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbCommentsDto zbComments : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务", zbComments.getTaskId());
            map.put("评论人", zbComments.getFromUid());
            map.put("被评论人", zbComments.getToUid());
            map.put("评论内容", zbComments.getComment());
            map.put("评论来源", zbComments.getCommentBy());
            map.put("速度评分", zbComments.getSpeedScore());
            map.put("质量评分", zbComments.getQualityScore());
            map.put("态度评分", zbComments.getAttitudeScore());
            map.put("评价", zbComments.getType());
            map.put("创建时间", zbComments.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
