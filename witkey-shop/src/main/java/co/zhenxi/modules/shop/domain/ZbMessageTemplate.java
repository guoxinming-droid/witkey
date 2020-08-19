/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.domain;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author guoke
* @date 2020-08-05
*/
@Data
@TableName("zb_message_template")
public class ZbMessageTemplate implements Serializable {

    /** 信息邮件配置编号 */
    @TableId
    private Integer id;


    /** 名称代号 */
    private String codeName;


    /** 信息邮件配置名称 */
    private String name;


    /** 消息模版 */
    @NotBlank
    private String content;


    /** 类型 1->系统消息 2->交易动态 */
    private Integer messageType;


    /** 是否开启 1->是 2->否 */
    private Integer isOpen;


    /** 站内信息 1->是 */
    @NotNull
    private Integer isOnSite;


    /** 发送邮件 1->是 */
    @NotNull
    private Integer isSendEmail;


    /** 模板变量个数 */
    @NotNull
    private Integer num;


    /** 变量名称 以逗号隔开 */
    private String variableStr;


    /** 发送短信 1->是 */
    @NotNull
    private Integer isSendMobile;


    /** 短信模板编号 */
    @NotBlank
    private String codeMobile;


    /** 短信模板编号内容 */
    @NotBlank
    private String mobileCodeContent;


    private Timestamp createdAt;


    private Timestamp updatedAt;


    public void copy(ZbMessageTemplate source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
