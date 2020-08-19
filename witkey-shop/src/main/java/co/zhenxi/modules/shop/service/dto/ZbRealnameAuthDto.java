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
public class ZbRealnameAuthDto implements Serializable {

    private Integer id;

    /** 关联用户id */
    private Integer uid;

    /** 用户名 */
    private String username;

    /** 用户真实姓名 */
    private String realname;

    /** 用户证件号 */
    private String cardNumber;

    /** 身份证正面 */
    private String cardFrontSide;

    /** 身份证背面 */
    private String cardBackDside;

    /** 持证验证图片 */
    private String validationImg;

    /** 认证状态 0：待验证 1：成功 2：失败 */
    private Integer status;

    /** 证件类型  1-身份证  2-护照 */
    private Integer cardType;

    /** 认证类型  1-身份认证  2-企业认证 */
    private Integer type;

    /** 认证通过时间 */
    private Timestamp authTime;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
