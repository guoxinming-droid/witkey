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
* @date 2020-07-22
*/
@Data
public class ZbTaskAttachmentDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 任务ID */
    private Integer taskId;

    /** 附件ID */
    private Integer attachmentId;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
