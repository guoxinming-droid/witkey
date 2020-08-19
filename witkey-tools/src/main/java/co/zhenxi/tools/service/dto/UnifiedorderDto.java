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
* @author Guoxm
* @date 2020-07-18
*/
@Data
public class UnifiedorderDto implements Serializable {

    private String returnCode;

    private String returnMsg;

    private String appid;

    private String mchId;

    private String deviceInfo;

    private String nonceStr;

    private String sign;

    private String resultCode;

    private String errCode;

    private String errCodeDes;

    private String tradeType;

    private String prepayId;

    /** id */
    private Integer id;

    private String body;

    private String notifyUrl;

    private String outTradeNo;

    private String spbillCreateIp;

    private Integer totalFee;
}
