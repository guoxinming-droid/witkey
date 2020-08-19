/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbRecommend;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbRecommendService;
import co.zhenxi.modules.shop.service.dto.ZbRecommendDto;
import co.zhenxi.modules.shop.service.dto.ZbRecommendQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbRecommendMapper;
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
//@CacheConfig(cacheNames = "zbRecommend")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbRecommendServiceImpl extends BaseServiceImpl<ZbRecommendMapper, ZbRecommend> implements ZbRecommendService {

    private final IGenerator generator;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbRecommendQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbRecommend> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbRecommendDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbRecommend> queryAll(ZbRecommendQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbRecommend.class, criteria));
    }


    @Override
    public void download(List<ZbRecommendDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbRecommendDto zbRecommend : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("推荐位", zbRecommend.getPositionId());
            map.put("类型", zbRecommend.getType());
            map.put("推荐编号", zbRecommend.getRecommendId());
            map.put("推荐类型", zbRecommend.getRecommendType());
            map.put("推荐名称", zbRecommend.getRecommendName());
            map.put("推荐图片", zbRecommend.getRecommendPic());
            map.put("跳转链接", zbRecommend.getUrl());
            map.put("开始时间", zbRecommend.getStartTime());
            map.put("结束时间", zbRecommend.getEndTime());
            map.put("排序", zbRecommend.getSort());
            map.put("是否开启 1-开启 2-关闭 3-删除", zbRecommend.getIsOpen());
            map.put("创建时间", zbRecommend.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
