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
import co.zhenxi.modules.shop.domain.ZbEmploy;
import co.zhenxi.modules.shop.domain.ZbGoods;
import co.zhenxi.modules.shop.domain.ZbUnionRights;
import co.zhenxi.modules.shop.service.ZbUnionRightsService;
import co.zhenxi.modules.shop.service.dto.ZbUnionRightsDto;
import co.zhenxi.modules.shop.service.dto.ZbUnionRightsQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbEmployMapper;
import co.zhenxi.modules.shop.service.mapper.ZbGoodsMapper;
import co.zhenxi.modules.shop.service.mapper.ZbUnionRightsMapper;
import co.zhenxi.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
* @date 2020-07-30
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbUnionRights")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbUnionRightsServiceImpl extends BaseServiceImpl<ZbUnionRightsMapper, ZbUnionRights> implements ZbUnionRightsService {

    private final IGenerator generator;
    private final  ZbUnionRightsMapper zbUnionRightsMapper;
    private final ZbGoodsMapper zbGoodsMapper;
    private final ZbEmployMapper zbEmployMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbUnionRightsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbUnionRights> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbUnionRightsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> ShopRightsList(ZbUnionRightsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        criteria.setIsDelete(0);
        PageInfo<ZbUnionRights> page = new PageInfo<>(queryAll(criteria));
        List<ZbUnionRightsDto> zbUnionRightsDTOS = generator.convert(page.getList(),ZbUnionRightsDto.class);
        for (ZbUnionRightsDto zbUnionRightsDTO : zbUnionRightsDTOS) {
            if(zbUnionRightsDTO.getObjectType().equals(2)){
                zbUnionRightsDTO.setZbGoods(zbGoodsMapper.selectById(new QueryWrapper<ZbGoods>().eq("id",zbUnionRightsDTO.getObjectId())));
            }else if(zbUnionRightsDTO.getObjectType().equals(1)){
                zbUnionRightsDTO.setZbEmploy(zbEmployMapper.selectById(new QueryWrapper<ZbEmploy>().eq("id",zbUnionRightsDTO.getObjectId())));
            }
        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", zbUnionRightsDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbUnionRights> ShopRightsList(ZbUnionRightsQueryCriteria criteria) {
        return zbUnionRightsMapper.ShopRightsList(QueryHelpPlus.getPredicate(ZbUnionRights.class, criteria));
    }


    @Override
    //@Cacheable
    public List<ZbUnionRights> queryAll(ZbUnionRightsQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbUnionRights.class, criteria));
    }


    @Override
    public void download(List<ZbUnionRightsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbUnionRightsDto zbUnionRights : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("维权类型", zbUnionRights.getType());
            map.put("对象关联", zbUnionRights.getObjectId());
            map.put("对象类型", zbUnionRights.getObjectType());
            map.put("维权描述", zbUnionRights.getDes());
            map.put("处理状态", zbUnionRights.getStatus());
            map.put("维权人", zbUnionRights.getFromUid());
            map.put("被维权人", zbUnionRights.getToUid());
            map.put("后台处理人", zbUnionRights.getHandelUid());
            map.put("维权处理时间", zbUnionRights.getHandledAt());
            map.put("软删除  1=>删除  0=>未删除", zbUnionRights.getIsDelete());
            map.put("维权方获得金额", zbUnionRights.getFromPrice());
            map.put("被维权方获得金额", zbUnionRights.getToPrice());
            map.put("维权创建时间", zbUnionRights.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
