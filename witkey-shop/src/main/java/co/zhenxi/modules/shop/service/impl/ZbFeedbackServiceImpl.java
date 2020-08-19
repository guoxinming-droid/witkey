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
import co.zhenxi.modules.shop.domain.ZbFeedback;
import co.zhenxi.modules.shop.service.ZbFeedbackService;
import co.zhenxi.modules.shop.service.dto.ZbFeedbackDto;
import co.zhenxi.modules.shop.service.dto.ZbFeedbackQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbFeedbackMapper;
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
* @author Guo xinming
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbFeedback")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbFeedbackServiceImpl extends BaseServiceImpl<ZbFeedbackMapper, ZbFeedback> implements ZbFeedbackService {

    private final IGenerator generator;
    private final ZbFeedbackMapper  zbFeedbackMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbFeedbackQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbFeedback> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbFeedbackDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbFeedback> queryAll(ZbFeedbackQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbFeedback.class, criteria));
    }


    @Override
    public void download(List<ZbFeedbackDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbFeedbackDto zbFeedback : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户", zbFeedback.getUid());
            map.put("用户手机", zbFeedback.getPhone());
            map.put("反馈内容", zbFeedback.getDesc());
            map.put("用户反馈类型", zbFeedback.getType());
            map.put("回复状态", zbFeedback.getStatus());
            map.put("回复内容", zbFeedback.getReplay());
            map.put("反馈时间", zbFeedback.getCreatedTime());
            map.put("反馈处理时间", zbFeedback.getHandleTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> feedbackList(Integer user,Integer status,String StartTime,String endTime, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbFeedback> page = new PageInfo<>(feedbackList(user,status,StartTime,endTime));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbFeedbackDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbFeedback> feedbackList(Integer user,Integer status,String startTime,String endTime) {
        String whereSql = " WHERE 1 = 1 ";
        if(user !=null && "1".equals(user)){
            whereSql += " AND uid <> 0 ";
        }else if(user !=null && "2".equals(user)){
            whereSql += " AND uid = 0 ";
        }else {
            whereSql += "";
        }
        if(status !=null && !"".equals(user)){
            whereSql += " AND status = "+status;
        }else {
            whereSql += "";
        }
        if(startTime !=null && !"".equals(startTime)){
            whereSql += " AND created_time >= "+startTime;
        }else{
            whereSql += "";
        }
        if(endTime !=null && !"".equals(endTime)){
            whereSql += " AND created_time >= "+endTime;
        }else{
            whereSql += "";
        }
        return zbFeedbackMapper.feedbackList(whereSql);
    }
}
