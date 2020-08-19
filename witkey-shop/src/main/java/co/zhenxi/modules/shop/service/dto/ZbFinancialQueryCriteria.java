/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import co.zhenxi.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Data
public class ZbFinancialQueryCriteria{

    /** 精确 */
    @Query
    private Integer action;

    /** 精确 */
    @Query(type = Query.Type.NOT_EQUAL)
    private Integer payType;

    /** 精确 */
    @Query
    private Integer uid;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
