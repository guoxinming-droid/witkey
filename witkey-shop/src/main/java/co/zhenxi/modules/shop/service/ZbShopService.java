/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.*;
import co.zhenxi.modules.shop.service.dto.ZbShopDto;
import co.zhenxi.modules.shop.service.dto.ZbShopQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author Guoxm
* @date 2020-07-16
*/
public interface ZbShopService  extends BaseService<ZbShop>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbShopQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbShopDto>
    */
    List<ZbShop> queryAll(ZbShopQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbShopDto> all, HttpServletResponse response) throws IOException;




    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> getRecommendShopList(ZbShopQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbShopDto>
     */
    List<ZbShop> getRecommendShopList(ZbShopQueryCriteria criteria);


    /**
     * 查询推荐商城首页推荐详情
     * @param id
     * @return
     */
    ZbShop getRecommendShopListById(Integer id);

    /**
     *  查询收藏店铺列表
     * @param uid 用户id
     * @return
     */
    List getCollectShop(Integer uid);

    /**
     * 获取作品和服务 套餐ID
     * @param size
     * @return
     */
    Map<String, Object> getShopByVip(Pageable size);

    /**
     * 获取推荐店铺  排序字段好评数
     * @param size
     * @return
     */
    Map<String, Object> getRecommendShop(Pageable size);

    /**
     * 店铺详情页
     * @param shopId 店铺ID
     * @return
     */
    ZbShop queryAllById(Integer shopId);

    /**
     * 获取服务商的作品
     * @param shopId
     * @return
     */
    Map getWorkById(String shopId,String type,String cateId,Pageable pageable);

    /**
     * 获取服务商成功案例
     * @param shopId
     * @param pageable
     * @return
     */
    Map getSuccessCaseById(String shopId,String cateId ,Pageable pageable);

    /**
     * 计数
     * @param shopId
     * @param type
     * @param pageable
     * @return
     */
    List<Map<String ,Object>> getGoodsCount(String shopId, String type, Pageable pageable);

    /**
     * 成功案例计数
     * @param shopId
     * @param pageable
     * @return
     */
    List<Map<String,Object>> getSuccessCaseCount(String shopId ,Pageable pageable);

    /**
     * 获取店铺评价
     * @param shopId
     * @param pageable
     * @return
     */
    Map<String,Object> getEvaluateByShopId(String shopId, Pageable pageable);

    /**
     * 根据用户获取ID
     * @param uid
     * @return
     */
    ZbShop getshopByuid(Integer uid);

    /**
     * 收藏店铺
     * @param uid
     * @param shopId
     * @return
     */
    void CollectionShop(Integer uid, Integer shopId);

//    /**
//     *
//     * @param shopId
//     * @return
//     */
//    ZbShop getShopById(Integer shopId);
}
