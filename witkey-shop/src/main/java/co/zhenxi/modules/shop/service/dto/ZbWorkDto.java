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
* @date 2020-07-23
*/
@Data
public class ZbWorkDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 任务ID */
    private Integer taskId;

    /** 被关注者id */
    private String desc;

    /** 状态 */
    private Integer status;

    /** 是否禁用稿件 */
    private Integer forbidden;

    /** 威客人员 */
    private Integer uid;

    /** 中标选中对象 */
    private Integer bidBy;

    /** 中标时间 */
    private Timestamp bidAt;

    /** 稿件创建时间 */
    private Timestamp createdAt;

    /** 威客报价金额 */
    private BigDecimal price;
}
