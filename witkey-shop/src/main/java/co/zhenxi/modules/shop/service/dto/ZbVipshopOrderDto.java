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
* @date 2020-07-27
*/
@Data
public class ZbVipshopOrderDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 订单编号 */
    private String code;

    /** 订单标题 */
    private String title;

    /** 用户名 */
    private Integer uid;

    /** 套餐 */
    private Integer packageId;

    /** 店铺 */
    private Integer shopId;

    /** 套餐时长(月) */
    private Integer timePeriod;

    /** 金额 */
    private BigDecimal cash;

    /** 订单状态 */
    private Integer status;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
