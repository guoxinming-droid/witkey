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
import co.zhenxi.modules.shop.domain.ZbSuccessCase;
import co.zhenxi.modules.shop.service.ZbSuccessCaseService;
import co.zhenxi.modules.shop.service.dto.ZbSuccessCaseDto;
import co.zhenxi.modules.shop.service.dto.ZbSuccessCaseQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbSuccessCaseMapper;
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
* @date 2020-07-30
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbSuccessCase")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbSuccessCaseServiceImpl extends BaseServiceImpl<ZbSuccessCaseMapper, ZbSuccessCase> implements ZbSuccessCaseService {

    private final IGenerator generator;
    private final ZbSuccessCaseMapper zbSuccessCaseMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbSuccessCaseQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbSuccessCase> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbSuccessCaseDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> successCaseList(ZbSuccessCaseQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbSuccessCase> page = new PageInfo<>(successCaseList(criteria));
//        List<ZbSuccessCaseDto> zbSuccessCaseDTOS = generator.convert(page.getList(),ZbUnionRightsDto.class);
//        for (ZbUnionRightsDto zbUnionRightsDTO : zbUnionRightsDTOS) {
//            if(zbUnionRightsDTO.getObjectType().equals(2)){
//                zbUnionRightsDTO.setZbGoods(zbGoodsMapper.selectById(new QueryWrapper<ZbGoods>().eq("id",zbUnionRightsDTO.getObjectId())));
//            }else if(zbUnionRightsDTO.getObjectType().equals(1)){
//                zbUnionRightsDTO.setZbEmploy(zbEmployMapper.selectById(new QueryWrapper<ZbEmploy>().eq("id",zbUnionRightsDTO.getObjectId())));
//            }
//        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbSuccessCaseDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbSuccessCase> successCaseList(ZbSuccessCaseQueryCriteria criteria) {
        return zbSuccessCaseMapper.successCaseList(QueryHelpPlus.getPredicate(ZbSuccessCase.class, criteria));
    }


    @Override
    //@Cacheable
    public List<ZbSuccessCase> queryAll(ZbSuccessCaseQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbSuccessCase.class, criteria));
    }


    @Override
    public void download(List<ZbSuccessCaseDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbSuccessCaseDto zbSuccessCase : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建用户ID", zbSuccessCase.getUid());
            map.put("发布人name", zbSuccessCase.getUsername());
            map.put("成功案例标题", zbSuccessCase.getTitle());
            map.put("成功案例描述", zbSuccessCase.getDes());
            map.put("成功案例跳转链接", zbSuccessCase.getUrl());
            map.put("成功案例封面", zbSuccessCase.getPic());
            map.put("成功案例分类", zbSuccessCase.getCateId());
            map.put("成功案例发布方式: 0->平台 1->用户", zbSuccessCase.getType());
            map.put("发布者id", zbSuccessCase.getPubUid());
            map.put("访问次数", zbSuccessCase.getViewCount());
            map.put("创建时间", zbSuccessCase.getCreatedAt());
            map.put("金额", zbSuccessCase.getCash());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<ZbSuccessCase> getSuccessCaseyUId(Integer uid) {
        return zbSuccessCaseMapper.getSuccessCaseyUId(uid);
    }



}
