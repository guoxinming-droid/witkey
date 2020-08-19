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
public class ZbEmployDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 雇佣标题 */
    private String title;

    /** 雇佣描述 */
    private String desc;

    /** 联系电话 */
    private String phone;

    /** 任务赏金 */
    private BigDecimal bounty;

    /** 托管状态 */
    private Integer bountyStatus;

    /** 截止时间 */
    private Timestamp deliveryDeadline;

    /** 状态 */
    private Integer status;

    /** 被雇佣人 */
    private Integer employeeUid;

    /** 雇佣人 */
    private Integer employerUid;

    /** 雇佣时间 */
    private Timestamp employedAt;

    private Integer employPercentage;

    private String seoTitle;

    private String seoKeywords;

    private String seoContent;

    /** 在此时间之后雇主就能够取消雇佣了 */
    private Timestamp cancelAt;

    /** 接受雇佣的最终时间限制 */
    private Timestamp exceptMaxAt;

    /** 结束时间 */
    private Timestamp endAt;

    /** 开始时间 */
    private Timestamp beginAt;

    /** 验收截止时间 */
    private Timestamp acceptDeadline;

    /** 验收时间 */
    private Timestamp acceptAt;

    /** 威客交付之后的维权期限 */
    private Timestamp rightAllowAt;

    /** 评价截止时间 */
    private Timestamp commentDeadline;

    /** 雇佣类型 */
    private Integer employType;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
