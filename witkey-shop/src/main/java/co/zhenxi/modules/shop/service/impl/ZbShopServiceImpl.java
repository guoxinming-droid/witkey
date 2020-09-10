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
import co.zhenxi.modules.shop.domain.*;
import co.zhenxi.modules.shop.service.ZbEmployCommentService;
import co.zhenxi.modules.shop.service.ZbGoodsService;
import co.zhenxi.modules.shop.service.ZbShopService;
import co.zhenxi.modules.shop.service.ZbSuccessCaseService;
import co.zhenxi.modules.shop.service.dto.ZbShopDto;
import co.zhenxi.modules.shop.service.dto.ZbShopQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.*;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    private final ZbSuccessCaseMapper zbSuccessCaseMapper;
    private final ZbEmployCommentService zbEmployCommentService;

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
        ZbShopAdvice shop = zbShopMapper.getShopByid(id);
        if(shop != null && "".equals(shop)){
            shop.setTagShop(zbTagShopMapper.getTagShopByShopId(id));
            shop.setGoods(zbGoodsService.selectGoodsByShopId(id,1));
        }
        return generator.convert(shop, ZbShop.class);
    }

    @Override
    public List getCollectShop(Integer uid) {
        if(uid<=0){
            return new ArrayList<>();
        }
        List<ZbShopAdvice> collectShop = zbShopMapper.getCollectShop(uid);
        for (ZbShopAdvice zbShopAdvice : collectShop) {
            List<String> collectShop1 = zbShopMapper.getCollectShop1(uid, zbShopAdvice.getId());
            zbShopAdvice.setList(collectShop1);
        }
        return collectShop;
    }

    /**
     * 获取作品和服务 套餐ID
     *
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> getShopByVip(Pageable size) {
        getPage(size);
        Page<Map<String , Object>> page = zbShopMapper.getShopByVip();
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", page.getResult());
        map.put("totalElements", page.getTotal());
        return map;
    }

    /**
     * 获取推荐店铺  排序字段好评数
     *
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> getRecommendShop(Pageable size) {
        getPage(size);
        Page<ZbShopAdvice> page = zbShopMapper.getRecommendShop();
        List<ZbShopAdvice> result = page.getResult();
        ZbShopAdvice zbShopAdvice = result.get(0);
        System.out.println(zbShopAdvice.getPName()+": "+zbShopAdvice.getSName());
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(result,ZbShopAdvice.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    /**
     * 店铺详情页
     *
     * @param shopId 店铺ID
     * @return
     */
    @Override
    public ZbShop queryAllById(Integer shopId) {
        ZbShop zbShop = zbShopMapper.selectById(shopId);
        return zbShop;
    }

    /**
     * 获取服务商的作品和服务
     *
     * @param shopId
     * @return
     */
    @Override
    public Map getWorkById(String shopId,String type,String cateId,Pageable pageable) {
        String whereSql = " WHERE 1 = 1 ";
        if(shopId != null && !"".equals(shopId)){
            whereSql+=" AND shop_id = "+shopId;
        }
        if(type != null && !"".equals(type)){
            whereSql+=" AND type = "+type;
        }
        if(cateId != null && !"".equals(cateId)){
            whereSql+=" AND cate_id = "+cateId;
        }
        whereSql+=" AND status = '1' ";
        whereSql+=" AND is_delete = '0' ";
        System.out.println(whereSql);
        getPage(pageable);
        Page<ZbGoods> zbGoods = zbGoodsMapper.selectGoodsByShopId(whereSql);
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(zbGoods.getResult(),ZbGoods.class));
        map.put("totalElements", zbGoods.getTotal());
        return map;


    }

    /**
     * 获取服务商成功案例
     *
     * @param shopId
     * @param pageable
     * @return
     */
    @Override
    public Map getSuccessCaseById(String shopId,String cateId, Pageable pageable) {
        ZbShop zbShop = zbShopMapper.selectById(shopId);
        if(null == zbShop){
            return null;
        }
        String whereSql = " WHERE pub_uid =  "+zbShop.getId();

        if(cateId != null && !"".equals(cateId)){
            whereSql+=" AND cate_id = "+cateId;
        }
        System.out.println(whereSql);
        getPage(pageable);
        Page<ZbSuccessCase> successCaseyS = zbSuccessCaseMapper.getSuccessCaseyUId(whereSql);
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(successCaseyS.getResult(),ZbSuccessCase.class));
        map.put("totalElements", successCaseyS.getTotal());
        return map;
    }

    /**
     * 计数
     *
     * @param shopId
     * @param type
     * @param pageable
     * @return
     */
    @Override
    public List<Map<String ,Object>> getGoodsCount(String shopId, String type, Pageable pageable) {

        return zbGoodsMapper.getCateNameAndCount(shopId,type);
    }

    /**
     * 成功案例计数
     *
     * @param shopId
     * @param pageable
     * @return
     */
    @Override
    public List<Map<String, Object>> getSuccessCaseCount(String shopId, Pageable pageable) {
        ZbShop zbShop = zbShopMapper.selectById(shopId);
        if(null == zbShop){
            return null;
        }
        return  zbSuccessCaseMapper.getSuccessCaseCount(zbShop.getId());
    }

    /**
     * 获取店铺评价
     *
     * @param shopId
     * @param pageable
     * @return
     */
    @Override
    public ZbEmployCommentAdvice getEvaluateByShopId(String shopId, Pageable pageable) {
        zbEmployCommentService.getEvaluateByShopId(shopId,pageable);
        return null;
    }


}
