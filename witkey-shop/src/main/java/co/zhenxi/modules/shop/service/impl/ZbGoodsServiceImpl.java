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
import co.zhenxi.modules.shop.domain.ZbGoods;
import co.zhenxi.modules.shop.domain.ZbGoodsComment;
import co.zhenxi.modules.shop.service.ZbGoodsCommentService;
import co.zhenxi.modules.shop.service.ZbGoodsService;
import co.zhenxi.modules.shop.service.dto.ZbGoodsDto;
import co.zhenxi.modules.shop.service.dto.ZbGoodsQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbGoodsMapper;
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
* @date 2020-07-29
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbGoods")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbGoodsServiceImpl extends BaseServiceImpl<ZbGoodsMapper, ZbGoods> implements ZbGoodsService {

    private final IGenerator generator;
    private final ZbGoodsMapper zbGoodsMapper;
    private final ZbGoodsCommentService zbGoodsCommentService;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbGoodsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbGoods> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbGoodsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbGoods> queryAll(ZbGoodsQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbGoods.class, criteria));
    }


    @Override
    public void download(List<ZbGoodsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbGoodsDto zbGoods : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" uid",  zbGoods.getUid());
            map.put(" shopId",  zbGoods.getShopId());
            map.put("商品二级分类编号", zbGoods.getCateId());
            map.put("商品标题", zbGoods.getTitle());
            map.put("商品价格单位 0：每件 1：每时", zbGoods.getUnit());
            map.put("商品类型 1：作品 2：服务", zbGoods.getType());
            map.put(" cash",  zbGoods.getCash());
            map.put("商品封面", zbGoods.getCover());
            map.put("商品状态 0：未审核 1：审核通过上架了  2：审核通过下架了  3：审核失败", zbGoods.getStatus());
            map.put("增值工具过期时间", zbGoods.getToolExpirationTime());
            map.put("是否推荐到商城 0：不推荐 1：推荐", zbGoods.getIsRecommend());
            map.put("推荐到商城截止时间", zbGoods.getRecommendEnd());
            map.put("卖出数量", zbGoods.getSalesNum());
            map.put("总评价数", zbGoods.getCommentsNum());
            map.put("好评数", zbGoods.getGoodComment());
            map.put("访问量", zbGoods.getViewNum());
            map.put("用户软删除 0表示未删除 1表示删除", zbGoods.getIsDelete());
            map.put("审核不通过原因", zbGoods.getRecommendText());
            map.put(" seoTitle",  zbGoods.getSeoTitle());
            map.put(" seoKeyword",  zbGoods.getSeoKeyword());
            map.put(" seoDesc",  zbGoods.getSeoDesc());
            map.put(" createdAt",  zbGoods.getCreatedAt());
            map.put(" updatedAt",  zbGoods.getUpdatedAt());
            map.put("商品描述", zbGoods.getDes());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> goodsServiceList(ZbGoodsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        criteria.setType(2);
        criteria.setIs_delete(0);
        PageInfo<ZbGoods> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbGoodsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> goodsList(ZbGoodsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        criteria.setType(1);
        criteria.setIs_delete(0);
        PageInfo<ZbGoods> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbGoodsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> getRecommendGoodsList(ZbGoodsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        criteria.setStatus(1);
        criteria.setIs_delete(0);
        PageInfo<ZbGoods> page = new PageInfo<>(getRecommendGoodsList(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbGoodsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbGoods> getRecommendGoodsList(ZbGoodsQueryCriteria criteria) {
        return zbGoodsMapper.getRecommendGoodsList(QueryHelpPlus.getPredicate(ZbGoods.class, criteria));
    }

    @Override
    public List<ZbGoods> selectGoodsByShopId(Integer shopId,Integer type) {
        String whereSql = " WHERE 1 = 1 ";
        if(shopId != null && "".equals(shopId)){
            whereSql+=" AND shop_id = "+shopId;
        }
        if(type != null && "".equals(type)){
            whereSql+=" AND type = "+type;
        }
            whereSql+=" AND status = '1' ";
            whereSql+=" AND is_delete = '0' ";
        return zbGoodsMapper.selectGoodsByShopId(whereSql);
    }

    @Override
    public Map<String, Object> getGoodsCommentByGoodId(Integer shopId,Integer type) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<ZbGoods> goods = selectGoodsByShopId(shopId,null);
        List<ZbGoodsComment> ZbGoodsComment = new ArrayList<ZbGoodsComment>();
        for(ZbGoods good:goods){
            ZbGoodsComment.addAll(zbGoodsCommentService.getGoodsCommentByGoodId(good.getId(),type));
        }
        map.put("ZbGoodsComment",ZbGoodsComment);
        return map;
    }






}
