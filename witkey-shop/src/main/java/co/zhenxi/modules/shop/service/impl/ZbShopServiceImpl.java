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
import co.zhenxi.modules.shop.domain.ZbShop;
import co.zhenxi.modules.shop.service.ZbGoodsService;
import co.zhenxi.modules.shop.service.ZbShopService;
import co.zhenxi.modules.shop.service.dto.ZbShopDto;
import co.zhenxi.modules.shop.service.dto.ZbShopQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbGoodsMapper;
import co.zhenxi.modules.shop.service.mapper.ZbShopMapper;
import co.zhenxi.modules.shop.service.mapper.ZbTagShopMapper;
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
* @author Guoxm
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbShop")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbShopServiceImpl extends BaseServiceImpl<ZbShopMapper, ZbShop> implements ZbShopService {

    private final IGenerator generator;
    private final ZbShopMapper zbShopMapper;
    private final ZbTagShopMapper zbTagShopMapper;
    private final ZbGoodsMapper zbGoodsMapper;
    private final ZbGoodsService zbGoodsService;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbShopQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbShop> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbShopDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbShop> queryAll(ZbShopQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbShop.class, criteria));
    }


    @Override
    public void download(List<ZbShopDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbShopDto zbShop : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("店主", zbShop.getUid());
            map.put("店铺类型", zbShop.getType());
            map.put("店铺封面", zbShop.getShopPic());
            map.put("店铺名称", zbShop.getShopName());
            map.put("店铺介绍", zbShop.getShopDesc());
            map.put("省", zbShop.getProvince());
            map.put("市", zbShop.getCity());
            map.put("店铺状态", zbShop.getStatus());
            map.put("评价数", zbShop.getTotalComment());
            map.put("好评数", zbShop.getGoodComment());
            map.put("店铺背景图", zbShop.getShopBg());
            map.put("seo标题", zbShop.getSeoTitle());
            map.put("seo关键词", zbShop.getSeoKeyword());
            map.put("seo描述", zbShop.getSeoDesc());
            map.put("是否推荐", zbShop.getIsRecommend());
            map.put("导航配置", zbShop.getNavRules());
            map.put("导航肤色", zbShop.getNavColor());
            map.put("轮播图", zbShop.getBannerRules());
            map.put("中部广告", zbShop.getCentralAd());
            map.put("底部广告", zbShop.getFooterAd());
            map.put("创建时间", zbShop.getCreatedAt());
            map.put("修改时间", zbShop.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> getRecommendShopList(ZbShopQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbShop> page = new PageInfo<>(getRecommendShopList(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbShopDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbShop> getRecommendShopList(ZbShopQueryCriteria criteria) {
        return zbShopMapper.getRecommendShopList(QueryHelpPlus.getPredicate(ZbShop.class, criteria));
    }

    @Override
    public ZbShop getRecommendShopListById(Integer id) {
        ZbShop shop = zbShopMapper.getShopByid(id);
        if(shop != null && "".equals(shop)){
            shop.setTagShop(zbTagShopMapper.getTagShopByShopId(id));
            shop.setGoods(zbGoodsService.selectGoodsByShopId(id,1));
        }
        return generator.convert(shop, ZbShop.class);
    }





}
