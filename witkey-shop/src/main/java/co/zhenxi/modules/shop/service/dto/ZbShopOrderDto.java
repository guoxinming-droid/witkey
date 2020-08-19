/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author guoke
* @date 2020-07-30
*/
@Data
public class ZbShopOrderDto implements Serializable {

    private Integer id;

    /** 订单号 */
    private String code;

    /** 订单标题 */
    private String title;

    /** 用户编号 */
    private Integer uid;

    /** 对象编号 */
    private Integer objectId;

    /** 对象类型 */
    private Integer objectType;

    private BigDecimal cash;

    /** 订单状态 */
    private Integer status;

    private Integer invoiceStatus;

    private String note;

    /** 创建订单时间 */
    private Timestamp createdAt;

    /** 支付时间 */
    private Timestamp payTime;

    /** 交易提成比例 */
    private Double tradeRate;

    /** 确认文件时间 */
    private Timestamp confirmTime;
}
