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
* @author Guoxm
* @date 2020-07-16
*/
@Data
public class ZbShopQueryCriteria{

    /** 精确 */
    @Query
    private Integer uid;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String shopName;

    /** 精确 */
    @Query
    private Integer status;


}
