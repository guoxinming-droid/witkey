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
* @author Guoxm
* @date 2020-07-16
*/
@Data
public class ZbTaskDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 任务标题 */
    private String title;

    /** 任务描述 */
    private String taskDesc;

    /** 任务类型 */
    private Integer typeId;

    /** 任务分类 */
    private Integer cateId;

    /** 联系电话 */
    private String phone;

    /** 地域限制 */
    private Integer regionLimit;

    /** 任务状态 */
    private Integer status;

    /** 赏金金额 */
    private BigDecimal bounty;

    /** 赏金状态 */
    private Integer bountyStatus;

    /** 审核时间 */
    private Timestamp verifiedAt;

    /** 任务开始时间 */
    private Timestamp beginAt;

    /** 任务结束时间 */
    private Timestamp endAt;

    /** 交稿结束时间 */
    private Timestamp deliveryDeadline;

    /** 选稿时间 */
    private Timestamp selectedWorkAt;

    /** 发布时间 */
    private Timestamp publicityAt;

    /** 验收期进入时间 */
    private Timestamp checkedAt;

    /** 双方互评开始时间 */
    private Timestamp commentAt;

    /** 展示赏金 */
    private BigDecimal showCash;

    /** 实付赏金 */
    private BigDecimal realCash;

    /** 已托管金额 */
    private BigDecimal depositCash;

    /** 省 */
    private Integer province;

    /** 城市 */
    private Integer city;

    /** 地区 */
    private Integer area;

    /** 浏览次数 */
    private Integer viewCount;

    /** 投稿数量 */
    private Integer deliveryCount;

    /** 用户编号 */
    private Integer uid;

    /** 用户名 */
    private String username;

    /** 服务商数量 */
    private Integer workerNum;

    /** 是否置顶 */
    private Integer topStatus;

    /** 搜索引擎屏蔽 */
    private Integer engineStatus;

    /** 稿件是否隐藏 */
    private Integer workStatus;

    /** 增值服务编号 */
    private String service;

    /** 成功抽成比率 */
    private Integer taskSuccessDrawRatio;

    /** 失败抽成比率 */
    private Integer taskFailDrawRatio;

    /** 交付状态 */
    private Integer keeStatus;

    /** 来源链接 */
    private String url;

    /** 来源网站 */
    private String siteName;

    /** 项目类型 */
    private Integer type;

    /** 二级标签 */
    private String tagName;

    /** 一级标签 */
    private String tagPname;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
