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
public class ZbOrderDto implements Serializable {

    /** 订单编号 */
    private Integer id;

    /** 订单编号 */
    private String code;

    /** 用户名 */
    private String userName;

    /** 来源 */
    private String froms;

    /** 订单标题 */
    private String title;

    /** 用户ID */
    private Integer uid;

    /** 任务订单的任务id */
    private Integer taskId;

    /** 订单金额 */
    private Double cash;

    /** 订单状态: 0 */
    private Integer status;

    /** 开票状态 */
    private Integer invoiceStatus;

    /** 订单备注 */
    private String note;

    /** 创建时间 */
    private Timestamp createdAt;
}
