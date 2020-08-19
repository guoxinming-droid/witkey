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
* @author guoke
* @date 2020-08-01
*/
@Data
public class ZbCashoutQueryCriteria{

    /** 精确 */
    @Query
    private Integer uid;

    /** 精确 */
    @Query
    private Integer payType;

    /** 状态 */
    @Query
    private Integer status;

    /** 精确 */
    @Query
    private String payAccount;

    /** 精确 */
    @Query
    private Integer cashoutType;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
