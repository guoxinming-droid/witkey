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
import co.zhenxi.modules.shop.domain.ZbPromote;
import co.zhenxi.modules.shop.service.ZbPromoteService;
import co.zhenxi.modules.shop.service.dto.ZbPromoteDto;
import co.zhenxi.modules.shop.service.dto.ZbPromoteQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbPromoteMapper;
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
* @date 2020-08-06
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbPromote")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbPromoteServiceImpl extends BaseServiceImpl<ZbPromoteMapper, ZbPromote> implements ZbPromoteService {

    private final IGenerator generator;
    private final ZbPromoteMapper zbPromoteMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbPromoteQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbPromote> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbPromoteDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbPromote> queryAll(ZbPromoteQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbPromote.class, criteria));
    }


    @Override
    public void download(List<ZbPromoteDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbPromoteDto zbPromote : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("推广人id", zbPromote.getFromUid());
            map.put("被推广人id", zbPromote.getToUid());
            map.put("推广成功获得推广金额", zbPromote.getPrice());
            map.put("完成推广的条件  1=>实名认证  2=>邮箱认证 3=>支付认证", zbPromote.getFinishConditions());
            map.put("推广类型  1=>注册推广", zbPromote.getType());
            map.put("推广状态  1=>推广中  2=>完成", zbPromote.getStatus());
            map.put("推广时间", zbPromote.getCreatedAt());
            map.put("完成推广时间", zbPromote.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> promoteRelation(String toName, String fromName, String startTime, String endTime, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbPromote> page = new PageInfo<>(promoteRelation(toName,fromName,startTime,endTime));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbPromoteDto.class));
        map.put("totalElements", page.getTotal());
        return  map;
    }

    @Override
    public List<ZbPromote> promoteRelation(String toName, String fromName, String startTime, String endTime) {
        String whereSql = " where 1 = 1  ";
        if(toName != null && !"".equals(toName)){
            whereSql += " AND u.name like %"+toName+"%";
        }
        if(fromName != null && !"".equals(fromName)){
            whereSql += " AND u1.name like %"+fromName+"%";
        }
        if(startTime != null && !"".equals(startTime)){
            whereSql += " AND p.created_at >=  startTime";
        }
        if(endTime != null && !"".equals(endTime)){
            whereSql += " AND p.created_at <=  endTime";
        }

        return zbPromoteMapper.promoteRelation(whereSql);
    }

    @Override
    public Map<String, Object> promoteFinance(String toName, String fromName, String startTime, String endTime, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbPromote> page = new PageInfo<>(promoteRelation(toName,fromName,startTime,endTime));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbPromoteDto.class));
        map.put("totalElements", page.getTotal());
        return  map;
    }

    @Override
    public List<ZbPromote> promoteFinance(String toName, String fromName, String startTime, String endTime) {
        String whereSql = " where 1 = 1  ";
        if(toName != null && !"".equals(toName)){
            whereSql += " AND u.name like %"+toName+"%";
        }
        if(fromName != null && !"".equals(fromName)){
            whereSql += " AND u1.name like %"+fromName+"%";
        }
        if(startTime != null && !"".equals(startTime)){
            whereSql += " AND p.created_at >=  startTime";
        }
        if(endTime != null && !"".equals(endTime)){
            whereSql += " AND p.created_at <=  endTime";
        }
        whereSql += "AND p.status = 2 ";
        return zbPromoteMapper.promoteRelation(whereSql);
    }
}
