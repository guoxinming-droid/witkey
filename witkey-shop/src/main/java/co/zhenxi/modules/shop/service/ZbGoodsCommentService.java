/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbGoodsComment;
import co.zhenxi.modules.shop.service.dto.ZbGoodsCommentDto;
import co.zhenxi.modules.shop.service.dto.ZbGoodsCommentQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-07-23
*/
public interface ZbGoodsCommentService  extends BaseService<ZbGoodsComment>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbGoodsCommentQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbGoodsCommentDto>
    */
    List<ZbGoodsComment> queryAll(ZbGoodsCommentQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbGoodsCommentDto> all, HttpServletResponse response) throws IOException;


    /**
     * 商品评价
     * @param shopId
     * @return
     */
    List<ZbGoodsComment> getGoodsCommentByGoodId(Integer shopId,Integer type);


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAllAndComment(ZbGoodsCommentQueryCriteria criteria, Pageable pageable);

}
