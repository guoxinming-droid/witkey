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
import co.zhenxi.modules.shop.domain.ZbWorkComments;
import co.zhenxi.modules.shop.service.ZbWorkCommentsService;
import co.zhenxi.modules.shop.service.dto.ZbWorkCommentsDto;
import co.zhenxi.modules.shop.service.dto.ZbWorkCommentsQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbWorkCommentsMapper;
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
//@CacheConfig(cacheNames = "zbWorkComments")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbWorkCommentsServiceImpl extends BaseServiceImpl<ZbWorkCommentsMapper, ZbWorkComments> implements ZbWorkCommentsService {

    private final IGenerator generator;
    private final ZbWorkCommentsMapper zbWorkCommentsMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbWorkCommentsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbWorkComments> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbWorkCommentsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbWorkComments> queryAll(ZbWorkCommentsQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbWorkComments.class, criteria));
    }


    @Override
    public void download(List<ZbWorkCommentsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbWorkCommentsDto zbWorkComments : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("稿件ID", zbWorkComments.getWorkId());
            map.put("评论内容", zbWorkComments.getComment());
            map.put("评论人ID", zbWorkComments.getUid());
            map.put("昵称", zbWorkComments.getNickname());
            map.put("所属任务ID", zbWorkComments.getTaskId());
            map.put("父级评论ID", zbWorkComments.getPid());
            map.put("创建时间", zbWorkComments.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<ZbWorkComments> getWorkCommentsById(long tsakId) {
        return zbWorkCommentsMapper.getWorkCommentsById(tsakId);
    }
}
