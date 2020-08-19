/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.domain;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @author Guoxm
* @date 2020-07-18
*/
@Data
@TableName("unifiedorder")
public class Unifiedorder implements Serializable {

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
    @TableId
    private Integer id;


    private String body;


    private String notifyUrl;


    private String outTradeNo;


    private String spbillCreateIp;


    private Integer totalFee;


    public void copy(Unifiedorder source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
