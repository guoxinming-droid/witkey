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
* @author Guoxm
* @date 2020-07-16
*/
@Data
public class ZbTaskReportDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 举报类型 */
    private Integer type;

    /** 举报的投稿 */
    private Integer taskId;

    /** 举报的投稿记录 */
    private Integer workId;

    /** 举报描述 */
    private String desc;

    /** 状态 */
    private Integer status;

    /** 举报人 */
    private Integer fromUid;

    /** 被举报人 */
    private Integer toUid;

    /** 附件 */
    private Integer attachmentIds;

    /** 处理方式  */
    private Integer handleType;

    /** 处理人 */
    private Integer handleUid;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 处理时间 */
    private Timestamp handledAt;
}
