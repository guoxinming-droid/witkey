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
* @author Guo
* @date 2020-07-20
*/
@Data
@TableName("wxpay_config")
public class WxpayConfig implements Serializable {

    @TableId
    private Integer id;


    /** 公众账号ID */
    @NotBlank
    private String appid;


    /** 商户号 */
    @NotBlank
    private String mchId;


    /** 设备号 */
    @NotBlank
    private String deviceInfo;


    /** 商户的key */
    @NotBlank
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


    public void copy(WxpayConfig source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
