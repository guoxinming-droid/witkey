/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbTaskRights;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbTaskRightsService;
import co.zhenxi.modules.shop.service.dto.ZbTaskRightsDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskRightsQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbTaskRightsMapper;
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
//@CacheConfig(cacheNames = "zbTaskRights")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbTaskRightsServiceImpl extends BaseServiceImpl<ZbTaskRightsMapper, ZbTaskRights> implements ZbTaskRightsService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbTaskRightsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTaskRights> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskRightsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbTaskRights> queryAll(ZbTaskRightsQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbTaskRights.class, criteria));
    }


    @Override
    public void download(List<ZbTaskRightsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbTaskRightsDto zbTaskRights : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("维权角色", zbTaskRights.getRole());
            map.put("维权类型", zbTaskRights.getType());
            map.put("维权任务", zbTaskRights.getTaskId());
            map.put("投稿记录", zbTaskRights.getWorkId());
            map.put("维权描述", zbTaskRights.getDesc());
            map.put("状态", zbTaskRights.getStatus());
            map.put("维权人", zbTaskRights.getFromUid());
            map.put("被维权人", zbTaskRights.getToUid());
            map.put("处理人", zbTaskRights.getHandleUid());
            map.put("创建时间", zbTaskRights.getCreatedAt());
            map.put("处理时间", zbTaskRights.getHandledAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
