/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author Guo
* @date 2020-07-20
*/
@Data
public class WxpayConfigDto implements Serializable {

    private Integer id;

    /** 公众账号ID */
    private String appid;

    /** 商户号 */
    private String mchId;

    /** 设备号 */
    private String deviceInfo;

    /** 商户的key */
    private String key;

    /** api请求地址 */
    private String url;

    /** 服务器异步通知页面路径 */
    private String notifyUrl;

    /** 服务器同步通知页面路径 */
    private String returnUrl;

    private String wxPackage;

    /** 用户名 */
    private String uid;
}
