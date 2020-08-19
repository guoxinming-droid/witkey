/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbInterview;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.ValidationUtil;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbInterviewService;
import co.zhenxi.modules.shop.service.dto.ZbInterviewDto;
import co.zhenxi.modules.shop.service.dto.ZbInterviewQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbInterviewMapper;
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
* @date 2020-07-27
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbInterview")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbInterviewServiceImpl extends BaseServiceImpl<ZbInterviewMapper, ZbInterview> implements ZbInterviewService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbInterviewQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbInterview> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbInterviewDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbInterview> queryAll(ZbInterviewQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbInterview.class, criteria));
    }


    @Override
    public void download(List<ZbInterviewDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbInterviewDto zbInterview : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("访谈标题", zbInterview.getTitle());
            map.put("用户编号", zbInterview.getUid());
            map.put("用户名", zbInterview.getUsername());
            map.put("店铺编号", zbInterview.getShopId());
            map.put("店铺名", zbInterview.getShopName());
            map.put("店铺封面", zbInterview.getShopCover());
            map.put("访谈内容", zbInterview.getDesc());
            map.put("浏览数", zbInterview.getViewCount());
            map.put("排序 倒序", zbInterview.getList());
            map.put("创建时间", zbInterview.getCreatedAt());
            map.put("修改时间", zbInterview.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
