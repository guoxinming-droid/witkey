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
public class ZbTaskRightsDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 维权角色 */
    private Integer role;

    /** 维权类型 */
    private Integer type;

    /** 维权任务 */
    private Integer taskId;

    /** 投稿记录 */
    private Integer workId;

    /** 维权描述 */
    private String desc;

    /** 状态 */
    private Integer status;

    /** 维权人 */
    private Integer fromUid;

    /** 被维权人 */
    private Integer toUid;

    /** 处理人 */
    private Integer handleUid;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 处理时间 */
    private Timestamp handledAt;

}
