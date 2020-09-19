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
import co.zhenxi.modules.shop.service.ZbGoodsCommentService;
import co.zhenxi.modules.shop.service.ZbGoodsService;
import co.zhenxi.modules.shop.service.ZbShopService;
import co.zhenxi.modules.shop.service.dto.ZbGoodsDto;
import co.zhenxi.modules.shop.service.dto.ZbGoodsQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbGoodsMapper;
import co.zhenxi.modules.shop.service.mapper.ZbShopMapper;
import co.zhenxi.utils.BeanUtil;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
    private final ZbShopMapper zbShopMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbGoodsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        if(criteria.getIs_delete() == null){
            criteria.setIs_delete(0);
        }
        PageInfo<ZbGoods> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        List<ZbGoods> list = page.getList();
        map.put("content", generator.convert(copyZbGoods(list), ZbGoodsAdvice.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    private List<ZbGoodsAdvice> copyZbGoods(List<ZbGoods> list){
        ArrayList<ZbGoodsAdvice> zbGoodsAdvices = new ArrayList<>();
        for (ZbGoods zbGoods : list) {
            Map<String ,Object>  map = zbShopMapper.selectByGoodsId(zbGoods.getId());
            System.out.println(map);
            ZbGoodsAdvice zbGoodsAdvice = new ZbGoodsAdvice();
            BeanUtils.copyProperties(zbGoods,zbGoodsAdvice);
            String cityName = (String)map.get("cityNmae");
            zbGoodsAdvice.setCityName(cityName);
            System.out.println(zbGoodsAdvice.getCityName());
            zbGoodsAdvices.add(zbGoodsAdvice);
        }
        return zbGoodsAdvices;
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

    /**
     * 获取作品和服务 排序字段 访问量
     *
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> getGoods(Pageable size,Integer type) {
        String sql = "";
        getPage(size);
        if(type !=null){
            sql = "and type = "+ type;
        }
        Page<ZbGoods> page = zbGoodsMapper.getGoods(sql);
        List<ZbGoods> list = page.getResult();
        for (ZbGoods zbGoods : list) {
            zbGoods.setCash(zbGoods.getCash().setScale(2, RoundingMode.HALF_UP));
        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(list, ZbGoods.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    /**
     * 作品及服务得数量
     *
     * @param shopId
     * @return
     */
    @Override
    public List<Map<String, Object>> getGoodsCountByShopId(Integer shopId) {
        ArrayList<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 1; i < 3 ; i++) {
           mapList.add( zbGoodsMapper.selectGoodsCountByShopId(i,shopId));
        }

        return mapList;
    }

    /**
     * 评分
     *
     * @param shopId
     * @return
     */
    @Override
    public Map<String, Object> getGoodsScoreByShopId(Integer shopId) {
        List<ZbGoodsComment> zbGoodsComments = zbGoodsMapper.selectGoodsCommentByShopId(shopId);
        double speedScore = 0;
        double qualityScore = 0;
        double attitudeScore = 0;
        if(zbGoodsComments!=null && zbGoodsComments.size()>0){
            for (ZbGoodsComment zbGoodsComment : zbGoodsComments) {
                //速度得分
                speedScore+=zbGoodsComment.getSpeedScore();
                //质量得分
                qualityScore+=zbGoodsComment.getQualityScore();
                //态度得分
                attitudeScore+=zbGoodsComment.getAttitudeScore();
            }
            speedScore/=zbGoodsComments.size();
            qualityScore/=zbGoodsComments.size();
            attitudeScore/=zbGoodsComments.size();
        }
        LinkedHashMap<String , Object> linkedHashMap = new LinkedHashMap<>(2);
        linkedHashMap.put("速度得分",speedScore);
        linkedHashMap.put("质量得分",qualityScore);
        linkedHashMap.put("态度得分",attitudeScore);

        return linkedHashMap;
    }

    /**
     * 获取好评差评
     *
     * @param shopId
     * @param type
     * @return
     */
    @Override
    public List<ZbGoodsComment> getGoodsScoreByShopIdAndType(Integer shopId, Integer type) {
        List<ZbGoodsComment> goodsCommentByGoodId = zbGoodsCommentService.getGoodsCommentByGoodId(shopId, type);
        return goodsCommentByGoodId;
    }

    /**
     * 获取商品分类
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getGoodsType() {
        return  zbGoodsMapper.getGoodsType();

    }

    @Override
    public ZbGoodsAdvice getGoodsByType(Integer goodsId) {
        return zbGoodsMapper.getGoodsByType(goodsId);
    }

    /**
     * 获取其他店铺作品
     *
     * @param goodsId
     * @param pageable
     * @return
     */
    @Override
    public List<ZbGoods> getGoodsByGoodsId(Integer goodsId, Pageable pageable) {
        getPage(pageable);
        Page<ZbGoods> page = zbGoodsMapper.getGoodsByOther(goodsId);
        return page.getResult();
    }

    /**
     * 订单前数据回显
     *
     * @param goodsIds
     * @param pageable
     * @return
     */
    @Override
    public List<Map<String, Object>>  GoodsPutOrder(Integer[] goodsIds, Pageable pageable) {
        HashMap<Object, Object> map = new HashMap<>(2);
        ArrayList<ZbGoodsAdvice> zbGoodsAdvices = new ArrayList<>();
        for (int i = 0; i < goodsIds.length; i++) {
            zbGoodsAdvices.add(zbGoodsMapper.selectGoodsById(goodsIds[i]));
        }
        for (ZbGoodsAdvice zbGoodsAdvice : zbGoodsAdvices) {
            if(null==map.get(zbGoodsAdvice)){
                map.put(zbGoodsAdvice,1);
            }else {
                int count = (int)map.get(zbGoodsAdvice)+1;
                map.put(zbGoodsAdvice,count);
            }
        }
        ArrayList<Map<String, Object>> maps = new ArrayList<>();

        Set<Object> keys = map.keySet();

        for (Object key : keys) {
            System.out.println(key.toString());
            HashMap<String, Object> map1 = new HashMap<>(3);
            ZbGoodsAdvice zbGoodsAdvice = (ZbGoodsAdvice) key;
            BigDecimal bigDecimal = new BigDecimal(0.0);
            map1.put("zbGoods",key);
            map1.put("count",map.get(key));
            map1.put("price",bigDecimal.add(zbGoodsAdvice.getCash()).multiply(new BigDecimal((int)map.get(key))));
            maps.add(map1);
        }

        //Map<String, Object> stringObjectMap = zbGoodsMapper.selectGoodsById(goodsIds);

        return  maps;
    }


}
