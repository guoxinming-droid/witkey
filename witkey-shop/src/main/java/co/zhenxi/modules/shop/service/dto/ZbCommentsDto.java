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
* @date 2020-07-23
*/
@Data
public class ZbCommentsDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 任务 */
    private Integer taskId;

    /** 评论人 */
    private Integer fromUid;

    /** 被评论人 */
    private Integer toUid;

    /** 评论内容 */
    private String comment;

    /** 评论来源 */
    private Integer commentBy;

    /** 速度评分 */
    private Double speedScore;

    /** 质量评分 */
    private Double qualityScore;

    /** 态度评分 */
    private Double attitudeScore;

    /** 评价 */
    private Integer type;

    /** 创建时间 */
    private Timestamp createdAt;


    private String fromName;

    private String toName;

    private String title;
}
