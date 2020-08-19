/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import co.zhenxi.modules.shop.domain.ZbEmploy;
import co.zhenxi.modules.shop.domain.ZbGoods;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* @author guoke
* @date 2020-07-30
*/
@Data
public class ZbUnionRightsDto implements Serializable {

    private Integer id;

    /** 维权类型 */
    private Integer type;

    /** 对象关联 */
    private Integer objectId;

    /** 对象类型 */
    private String objectType;

    /** 维权描述 */
    private String des;

    /** 处理状态 */
    private Integer status;

    /** 维权人 */
    private Integer fromUid;

    /** 被维权人 */
    private Integer toUid;

    /** 后台处理人 */
    private Integer handelUid;

    /** 维权处理时间 */
    private Timestamp handledAt;

    /** 软删除  1=>删除  0=>未删除 */
    private Integer isDelete;

    /** 维权方获得金额 */
    private BigDecimal fromPrice;

    /** 被维权方获得金额 */
    private BigDecimal toPrice;

    /** 维权创建时间 */
    private Timestamp createdAt;

    private ZbGoods zbGoods;

    private ZbEmploy zbEmploy;

}
