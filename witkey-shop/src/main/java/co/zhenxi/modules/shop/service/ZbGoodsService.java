/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbGoods;
import co.zhenxi.modules.shop.service.dto.ZbGoodsDto;
import co.zhenxi.modules.shop.service.dto.ZbGoodsQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-07-29
*/
public interface ZbGoodsService  extends BaseService<ZbGoods>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbGoodsQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbGoodsDto>
    */
    List<ZbGoods> queryAll(ZbGoodsQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbGoodsDto> all, HttpServletResponse response) throws IOException;

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> goodsServiceList(ZbGoodsQueryCriteria criteria, Pageable pageable);

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> goodsList(ZbGoodsQueryCriteria criteria, Pageable pageable);


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> getRecommendGoodsList(ZbGoodsQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbGoodsDto>
     */
    List<ZbGoods> getRecommendGoodsList(ZbGoodsQueryCriteria criteria);


    /**
     *
     * @param shopId
     * @param type
     * @return
     */
    List<ZbGoods> selectGoodsByShopId(Integer shopId,Integer type);


    /**
     *
     * @param id
     * @return
     */
    Map<String, Object> getGoodsCommentByGoodId(Integer id,Integer type);


    /**
     * 获取作品和服务 排序字段 访问量
     * @param size
     * @return
     */
    Map<String, Object> getGoods(Pageable size);

    /**
     * 作品及服务得数量
     * @param shopId
     * @return
     */
    List<Map<String, Object>>  getGoodsCountByShopId(Integer shopId);


    /**
     * 评分
     * @param shopId
     * @return
     */
    Map<String, Object>  getGoodsScoreByShopId(Integer shopId);
}
