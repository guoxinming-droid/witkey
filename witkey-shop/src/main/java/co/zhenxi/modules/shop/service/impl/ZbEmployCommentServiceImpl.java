/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbEmployComment;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.modules.shop.domain.ZbEmployCommentAdvice;
import co.zhenxi.modules.shop.service.mapper.ZbEmployMapper;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbEmployCommentService;
import co.zhenxi.modules.shop.service.dto.ZbEmployCommentDto;
import co.zhenxi.modules.shop.service.dto.ZbEmployCommentQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbEmployCommentMapper;
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
* @author guoke
* @date 2020-07-23
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbEmployComment")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbEmployCommentServiceImpl extends BaseServiceImpl<ZbEmployCommentMapper, ZbEmployComment> implements ZbEmployCommentService {

    private final IGenerator generator;

    private final ZbEmployCommentMapper zbEmployCommentMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbEmployCommentQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbEmployComment> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbEmployCommentDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbEmployComment> queryAll(ZbEmployCommentQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbEmployComment.class, criteria));
    }


    @Override
    public void download(List<ZbEmployCommentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbEmployCommentDto zbEmployComment : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" employId",  zbEmployComment.getEmployId());
            map.put(" fromUid",  zbEmployComment.getFromUid());
            map.put(" toUid",  zbEmployComment.getToUid());
            map.put(" comment",  zbEmployComment.getComment());
            map.put("评价对象", zbEmployComment.getCommentBy());
            map.put("速度分数 1-5", zbEmployComment.getSpeedScore());
            map.put("质量分数 1-5", zbEmployComment.getQualityScore());
            map.put("态度分数 1-5", zbEmployComment.getAttitudeScore());
            map.put("评价类型 1表示好评 2表示中评 3表示差评", zbEmployComment.getType());
            map.put("创建时间", zbEmployComment.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 获取评价
     *
     * @param shopId
     * @param pageable
     * @return
     */
    @Override
    public ZbEmployCommentAdvice getEvaluateByShopId(String shopId, Pageable pageable) {

        return zbEmployCommentMapper.getEvaluateByShopId(shopId);
    }


}
