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
* @date 2020-08-05
*/
@Data
public class ZbMessageTemplateDto implements Serializable {

    /** 信息邮件配置编号 */
    private Integer id;

    /** 名称代号 */
    private String codeName;

    /** 信息邮件配置名称 */
    private String name;

    /** 消息模版 */
    private String content;

    /** 类型 1->系统消息 2->交易动态 */
    private Integer messageType;

    /** 是否开启 1->是 2->否 */
    private Integer isOpen;

    /** 站内信息 1->是 */
    private Integer isOnSite;

    /** 发送邮件 1->是 */
    private Integer isSendEmail;

    /** 模板变量个数 */
    private Integer num;

    /** 变量名称 以逗号隔开 */
    private String variableStr;

    /** 发送短信 1->是 */
    private Integer isSendMobile;

    /** 短信模板编号 */
    private String codeMobile;

    /** 短信模板编号内容 */
    private String mobileCodeContent;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
