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
import co.zhenxi.modules.shop.domain.ZbTaskAttachment;
import co.zhenxi.modules.shop.service.ZbTaskAttachmentService;
import co.zhenxi.modules.shop.service.dto.ZbTaskAttachmentDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskAttachmentQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbTaskAttachmentMapper;
import co.zhenxi.tools.service.LocalStorageService;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
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
//@CacheConfig(cacheNames = "zbTaskAttachment")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbTaskAttachmentServiceImpl extends BaseServiceImpl<ZbTaskAttachmentMapper, ZbTaskAttachment> implements ZbTaskAttachmentService {

    private final IGenerator generator;
    private final ZbTaskAttachmentMapper zbTaskAttachmentMapper;
    private final LocalStorageService localStorageService;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbTaskAttachmentQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTaskAttachment> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskAttachmentDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbTaskAttachment> queryAll(ZbTaskAttachmentQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbTaskAttachment.class, criteria));
    }


    @Override
    public void download(List<ZbTaskAttachmentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbTaskAttachmentDto zbTaskAttachment : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务ID", zbTaskAttachment.getTaskId());
            map.put("附件ID", zbTaskAttachment.getAttachmentId());
            map.put(" createdAt",  zbTaskAttachment.getCreatedAt());
            map.put(" updatedAt",  zbTaskAttachment.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public ZbTaskAttachment getByTaskId(long taskId) {
        return zbTaskAttachmentMapper.getByTaskId(taskId);
    }

    @Override
    public void insert(ZbTaskAttachment zbTaskAttachment) {
        zbTaskAttachment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        zbTaskAttachment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        zbTaskAttachmentMapper.insert(zbTaskAttachment);
    }
}
