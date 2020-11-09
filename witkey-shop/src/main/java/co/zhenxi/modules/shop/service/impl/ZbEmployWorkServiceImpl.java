/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbEmployWork;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.ValidationUtil;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbEmployWorkService;
import co.zhenxi.modules.shop.service.dto.ZbEmployWorkDto;
import co.zhenxi.modules.shop.service.dto.ZbEmployWorkQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbEmployWorkMapper;
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
* @date 2020-08-21
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbEmployWork")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbEmployWorkServiceImpl extends BaseServiceImpl<ZbEmployWorkMapper, ZbEmployWork> implements ZbEmployWorkService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbEmployWorkQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbEmployWork> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbEmployWorkDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbEmployWork> queryAll(ZbEmployWorkQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbEmployWork.class, criteria));
    }


    @Override
    public void download(List<ZbEmployWorkDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbEmployWorkDto zbEmployWork : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("稿件描述", zbEmployWork.getDesc());
            map.put("稿件id", zbEmployWork.getEmployId());
            map.put("状态 0表示没有验收 1表示验收", zbEmployWork.getStatus());
            map.put("交稿威客id", zbEmployWork.getUid());
            map.put("文件后缀", zbEmployWork.getType());
            map.put("创建时间", zbEmployWork.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
