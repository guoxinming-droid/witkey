/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbRecommendPosition;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbRecommendPositionService;
import co.zhenxi.modules.shop.service.dto.ZbRecommendPositionDto;
import co.zhenxi.modules.shop.service.dto.ZbRecommendPositionQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbRecommendPositionMapper;
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
//@CacheConfig(cacheNames = "zbRecommendPosition")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbRecommendPositionServiceImpl extends BaseServiceImpl<ZbRecommendPositionMapper, ZbRecommendPosition> implements ZbRecommendPositionService {

    private final IGenerator generator;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbRecommendPositionQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbRecommendPosition> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbRecommendPositionDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbRecommendPosition> queryAll(ZbRecommendPositionQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbRecommendPosition.class, criteria));
    }


    @Override
    public void download(List<ZbRecommendPositionDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbRecommendPositionDto zbRecommendPosition : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("推荐位名称", zbRecommendPosition.getName());
            map.put("推荐位别名", zbRecommendPosition.getCode());
            map.put("推荐位位置描述", zbRecommendPosition.getPosition());
            map.put("推荐位图片", zbRecommendPosition.getPic());
            map.put("推荐数量", zbRecommendPosition.getNum());
            map.put("状态", zbRecommendPosition.getIsOpen());
            map.put("创建时间", zbRecommendPosition.getCreatedAt());
            map.put("修改时间", zbRecommendPosition.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
