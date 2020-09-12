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
import co.zhenxi.modules.shop.domain.ZbGoodsComment;
import co.zhenxi.modules.shop.service.ZbGoodsCommentService;
import co.zhenxi.modules.shop.service.dto.ZbGoodsCommentDto;
import co.zhenxi.modules.shop.service.dto.ZbGoodsCommentQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbGoodsCommentMapper;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author guoke
* @date 2020-07-23
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbGoodsComment")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbGoodsCommentServiceImpl extends BaseServiceImpl<ZbGoodsCommentMapper, ZbGoodsComment> implements ZbGoodsCommentService {

    private final IGenerator generator;
    private final ZbGoodsCommentMapper  zbGoodsCommentMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbGoodsCommentQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbGoodsComment> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbGoodsCommentDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbGoodsComment> queryAll(ZbGoodsCommentQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbGoodsComment.class, criteria));
    }


    @Override
    public void download(List<ZbGoodsCommentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbGoodsCommentDto zbGoodsComment : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("商品编号", zbGoodsComment.getGoodsId());
            map.put("用户", zbGoodsComment.getUid());
            map.put("商品评价对象", zbGoodsComment.getCommentBy());
            map.put("速度得分", zbGoodsComment.getSpeedScore());
            map.put("质量得分", zbGoodsComment.getQualityScore());
            map.put("态度得分", zbGoodsComment.getAttitudeScore());
            map.put("评价内容", zbGoodsComment.getCommentDesc());
            map.put("评价类型", zbGoodsComment.getType());
            map.put("评价时间", zbGoodsComment.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<ZbGoodsComment> getGoodsCommentByGoodId(Integer shopId,Integer type) {
        String whereSql = "where 1=1 ";
        if(shopId != null && "".equals(shopId)){
            whereSql +=" AND gc.shop_id = "+shopId;
        }
        if(type != null && "".equals(type)){
            whereSql +=" AND gc.type = "+type;
        }
        return zbGoodsCommentMapper.getGoodsCommentByGoodId(whereSql);
    }

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> queryAllAndComment(ZbGoodsCommentQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbGoodsComment> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbGoodsCommentDto.class));
        map.put("totalElements", page.getTotal());
        Map<String, Object> map1 = zbGoodsCommentMapper.getAnyCommentByGoodsId(criteria.getGoodsId());
        if(map1==null){
            map.put("好评率","100%");
            map.put("速度得分",0);
            map.put("态度得分",0);
            map.put("质量得分",0);
        }else {
            Set<String> keys = map1.keySet();
            for (String key : keys) {
                map.put(key, map1.get(key));
            }
        }
        return map;
    }
}
