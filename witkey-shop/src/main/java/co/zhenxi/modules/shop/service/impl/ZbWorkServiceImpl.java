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
import co.zhenxi.modules.shop.domain.ZbWork;
import co.zhenxi.modules.shop.service.ZbWorkService;
import co.zhenxi.modules.shop.service.dto.ZbWorkDto;
import co.zhenxi.modules.shop.service.dto.ZbWorkQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbWorkMapper;
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
//@CacheConfig(cacheNames = "zbWork")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbWorkServiceImpl extends BaseServiceImpl<ZbWorkMapper, ZbWork> implements ZbWorkService {

    private final IGenerator generator;
    private final ZbWorkMapper zbWorkMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbWorkQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbWork> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbWorkDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbWork> queryAll(ZbWorkQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbWork.class, criteria));
    }


    @Override
    public void download(List<ZbWorkDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbWorkDto zbWork : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务ID", zbWork.getTaskId());
            map.put("被关注者id", zbWork.getDesc());
            map.put("状态", zbWork.getStatus());
            map.put("是否禁用稿件", zbWork.getForbidden());
            map.put("威客人员", zbWork.getUid());
            map.put("中标选中对象", zbWork.getBidBy());
            map.put("中标时间", zbWork.getBidAt());
            map.put("稿件创建时间", zbWork.getCreatedAt());
            map.put("威客报价金额", zbWork.getPrice());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<ZbWork> getWorkByTaskId(long taskId) {
        return zbWorkMapper.getWorkByTaskId(taskId);
    }
}
