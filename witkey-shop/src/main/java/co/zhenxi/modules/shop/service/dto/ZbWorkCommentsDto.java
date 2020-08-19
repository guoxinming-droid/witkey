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
public class ZbWorkCommentsDto implements Serializable {

    private Integer id;

    /** 稿件ID */
    private Integer workId;

    /** 评论内容 */
    private String comment;

    /** 评论人ID */
    private Integer uid;

    /** 昵称 */
    private String nickname;

    /** 所属任务ID */
    private Integer taskId;

    /** 父级评论ID */
    private Integer pid;

    /** 创建时间 */
    private Timestamp createdAt;
}
