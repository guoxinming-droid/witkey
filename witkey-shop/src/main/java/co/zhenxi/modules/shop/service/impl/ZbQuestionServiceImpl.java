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
import co.zhenxi.modules.shop.domain.ZbQuestion;
import co.zhenxi.modules.shop.service.ZbQuestionService;
import co.zhenxi.modules.shop.service.dto.ZbQuestionDto;
import co.zhenxi.modules.shop.service.dto.ZbQuestionQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbQuestionMapper;
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
//@CacheConfig(cacheNames = "zbQuestion")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbQuestionServiceImpl extends BaseServiceImpl<ZbQuestionMapper, ZbQuestion> implements ZbQuestionService {

    private final IGenerator generator;
    private final ZbQuestionMapper zbQuestionMapper;
    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbQuestionQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbQuestion> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbQuestionDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbQuestion> queryAll(ZbQuestionQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbQuestion.class, criteria));
    }


    @Override
    public void download(List<ZbQuestionDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbQuestionDto zbQuestion : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("问题浏览次数", zbQuestion.getNum());
            map.put("问题的描述", zbQuestion.getDiscription());
            map.put("问题是否解决 1表示发布 2表示审核通过 3表示已经回答 4表示问题解决 5表示审核失败", zbQuestion.getStatus());
            map.put("提问者uid", zbQuestion.getUid());
            map.put("提问时间", zbQuestion.getTime());
            map.put("审核时间", zbQuestion.getVerifyAt());
            map.put("问题类别", zbQuestion.getCategory());
            map.put("问题被回答次数", zbQuestion.getAnswernum());
            map.put("点赞次数", zbQuestion.getPraisenum());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> getZbQuestionsList(String userName, Integer category, Integer status, String startTime, String endTime, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbQuestion> page = new PageInfo<>(getZbQuestionsList(userName,category,status,startTime,endTime));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbQuestionDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbQuestion> getZbQuestionsList(String userName, Integer category, Integer status, String startTime, String endTime) {
        String whereSql = " WHERE 1 = 1 ";
        if(userName !=null && "".equals(userName)){
            whereSql +=" AND u.name like %"+userName+"%";
        }else{
            whereSql +="";
        }
        if(category !=null && "".equals(category)){
            whereSql +=" AND q.category = "+category;
        }else{
            whereSql +="";
        }
        if(status !=null && "".equals(status)){
            whereSql +=" AND q.status = "+status;
        }else{
            whereSql +="";
        }
        if(startTime !=null && "".equals(startTime)){
            whereSql +=" AND q.time >= "+startTime;
        }else{
            whereSql +="";
        }
        if(endTime !=null && "".equals(endTime)){
            whereSql +=" AND q.endTime >= "+endTime;
        }else{
            whereSql +="";
        }
        return zbQuestionMapper.getZbQuestionsList(whereSql);
    }

    @Override
    public Boolean onStatus(Integer id, int status) {
        return  zbQuestionMapper.updateOnstatus(id,status);
    }

}
