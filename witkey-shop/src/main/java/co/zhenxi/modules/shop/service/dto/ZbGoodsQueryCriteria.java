/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import co.zhenxi.annotation.Query;
import lombok.Data;

/**
* @author guoke
* @date 2020-07-29
*/
@Data
public class ZbGoodsQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String title;

    /** 精确 */
    @Query
    private Integer status;

    /** 精确 */
    @Query
    private Integer type;

    /** 精确 */
    @Query
    private Integer is_delete;
}
