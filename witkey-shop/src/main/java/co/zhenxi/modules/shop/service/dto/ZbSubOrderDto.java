/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author guoke
* @date 2020-08-01
*/
@Data
public class ZbSubOrderDto implements Serializable {

    private Integer id;

    /** 子订单标题 */
    private String title;

    /** 子订单备注 */
    private String note;

    /** 子订单金额 */
    private Double cash;

    /** 创建用户ID */
    private Integer uid;

    /** 父订单ID */
    private String orderId;

    /** 父订单编号 */
    private String orderCode;

    /** 对象ID(TASK,SERVICE,TOOL) */
    private Integer productId;

    /** 对象类型:1:TASK,2:SERVICE,3:TOOL */
    private Integer productType;

    /** 子订单状态 */
    private Integer status;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
