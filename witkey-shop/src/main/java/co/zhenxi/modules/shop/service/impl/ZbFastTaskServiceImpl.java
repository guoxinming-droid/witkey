/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbFastTask;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbFastTaskService;
import co.zhenxi.modules.shop.service.dto.ZbFastTaskDto;
import co.zhenxi.modules.shop.service.dto.ZbFastTaskQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbFastTaskMapper;
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
* @author Guo
* @date 2020-07-21
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbFastTask")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbFastTaskServiceImpl extends BaseServiceImpl<ZbFastTaskMapper, ZbFastTask> implements ZbFastTaskService {

    private final IGenerator generator;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbFastTaskQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbFastTask> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbFastTaskDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbFastTask> queryAll(ZbFastTaskQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbFastTask.class, criteria));
    }


    @Override
    public void download(List<ZbFastTaskDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbFastTaskDto zbFastTask : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务标题", zbFastTask.getTitle());
            map.put("用户名称", zbFastTask.getUid());
            map.put("任务ID", zbFastTask.getTaskId());
            map.put("发布状态", zbFastTask.getStatus());
            map.put("创建时间", zbFastTask.getCreatedAt());
            map.put("修改时间", zbFastTask.getUpdatedAt());
            map.put("项目类型", zbFastTask.getTaskType());
            map.put("手机号", zbFastTask.getMobile());
            map.put("描述", zbFastTask.getDes());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
