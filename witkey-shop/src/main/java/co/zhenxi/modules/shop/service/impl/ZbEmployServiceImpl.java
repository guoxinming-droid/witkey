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
import co.zhenxi.modules.shop.service.ZbEmployService;
import co.zhenxi.modules.shop.service.dto.ZbEmployDto;
import co.zhenxi.modules.shop.service.dto.ZbEmployQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbEmployMapper;
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
* @date 2020-07-27
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbEmploy")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbEmployServiceImpl extends BaseServiceImpl<ZbEmployMapper, ZbEmploy> implements ZbEmployService {

    private final IGenerator generator;
    private final ZbEmployMapper zbEmployMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbEmployQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbEmploy> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbEmployDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbEmploy> queryAll(ZbEmployQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbEmploy.class, criteria));
    }

    @Override
    public Map<String, Object> serviceOrderList(ZbEmployQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbEmploy> page = new PageInfo<>(serviceOrderList(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbEmployDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbEmploy> serviceOrderList(ZbEmployQueryCriteria criteria) {
        zbEmployMapper.serviceOrderList(QueryHelpPlus.getPredicate(ZbEmploy.class, criteria));
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbEmploy.class, criteria));
    }


    @Override
    public void download(List<ZbEmployDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbEmployDto zbEmploy : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("雇佣标题", zbEmploy.getTitle());
            map.put("雇佣描述", zbEmploy.getDesc());
            map.put("联系电话", zbEmploy.getPhone());
            map.put("任务赏金", zbEmploy.getBounty());
            map.put("托管状态", zbEmploy.getBountyStatus());
            map.put("截止时间", zbEmploy.getDeliveryDeadline());
            map.put("状态", zbEmploy.getStatus());
            map.put("被雇佣人", zbEmploy.getEmployeeUid());
            map.put("雇佣人", zbEmploy.getEmployerUid());
            map.put("雇佣时间", zbEmploy.getEmployedAt());
            map.put(" employPercentage",  zbEmploy.getEmployPercentage());
            map.put(" seoTitle",  zbEmploy.getSeoTitle());
            map.put(" seoKeywords",  zbEmploy.getSeoKeywords());
            map.put(" seoContent",  zbEmploy.getSeoContent());
            map.put("在此时间之后雇主就能够取消雇佣了", zbEmploy.getCancelAt());
            map.put("接受雇佣的最终时间限制", zbEmploy.getExceptMaxAt());
            map.put("结束时间", zbEmploy.getEndAt());
            map.put("开始时间", zbEmploy.getBeginAt());
            map.put("验收截止时间", zbEmploy.getAcceptDeadline());
            map.put("验收时间", zbEmploy.getAcceptAt());
            map.put("威客交付之后的维权期限", zbEmploy.getRightAllowAt());
            map.put("评价截止时间", zbEmploy.getCommentDeadline());
            map.put("雇佣类型", zbEmploy.getEmployType());
            map.put(" createdAt",  zbEmploy.getCreatedAt());
            map.put(" updatedAt",  zbEmploy.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
