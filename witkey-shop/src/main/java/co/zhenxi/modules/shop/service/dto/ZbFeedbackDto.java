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
* @author Guo xinming
* @date 2020-07-16
*/
@Data
public class ZbFeedbackDto implements Serializable {

    private Integer id;

    /** 用户 */
    private Integer uid;

    /** 用户手机 */
    private String phone;

    /** 反馈内容 */
    private String desc;

    /** 用户反馈类型 */
    private Integer type;

    /** 回复状态 */
    private Integer status;

    /** 回复内容 */
    private String replay;

    /** 反馈时间 */
    private Timestamp createdTime;

    /** 反馈处理时间 */
    private Timestamp handleTime;
}
