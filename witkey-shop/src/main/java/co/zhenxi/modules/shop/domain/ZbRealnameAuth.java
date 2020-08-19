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
@TableName("zb_realname_auth")
public class ZbRealnameAuth implements Serializable {

    @TableId
    private Integer id;


    /** 关联用户id */
    @NotNull
    private Integer uid;


    /** 用户名 */
    @NotBlank
    private String username;


    /** 用户真实姓名 */
    @NotBlank
    private String realname;


    /** 用户证件号 */
    @NotBlank
    private String cardNumber;


    /** 身份证正面 */
    @NotBlank
    private String cardFrontSide;


    /** 身份证背面 */
    @NotBlank
    private String cardBackDside;


    /** 持证验证图片 */
    @NotBlank
    private String validationImg;


    /** 认证状态 0：待验证 1：成功 2：失败 */
    @NotNull
    private Integer status;


    /** 证件类型  1-身份证  2-护照 */
    @NotNull
    private Integer cardType;


    /** 认证类型  1-身份认证  2-企业认证 */
    @NotNull
    private Integer type;


    /** 认证通过时间 */
    private Timestamp authTime;


    private Timestamp createdAt;


    private Timestamp updatedAt;


    public void copy(ZbRealnameAuth source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
