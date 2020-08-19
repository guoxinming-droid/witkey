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
public class ZbUsersDto implements Serializable {

    /** 用户编号 */
    private Integer id;

    /** 用户名 */
    private String name;

    /** 用户邮箱 */
    private String email;

    /** 用户手机注册 */
    private String mobile;

    /** 用户邮箱认证状态 */
    private Integer emailStatus;

    /** 用户密码 */
    private String password;

    /** 支付密码 */
    private String alternatePassword;

    /** 随机码 */
    private String salt;

    /** 账户状态  */
    private Integer status;

    /** 找回密码邮件过期时间 */
    private Timestamp overdueDate;

    /** 找回密码随机码 */
    private String validationCode;

    /** 重置密码邮件过期时间 */
    private Timestamp expireDate;

    /** 重置密码验证随机码 */
    private String resetPasswordCode;

    /** token */
    private String rememberToken;

    /** 最后一次登录时间 */
    private Timestamp lastLoginTime;

    /** 注册来源 */
    private Integer source;

    /** 是否会员 */
    private Integer isvip;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
