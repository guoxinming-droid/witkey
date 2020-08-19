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
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author Guo xinming
* @date 2020-07-16
*/
@Data
@TableName("zb_bank_auth")
public class ZbBankAuth implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 用户编号 */
    @NotNull
    private Integer uid;


    /** 用户名 */
    @NotBlank
    private String username;


    /** 真实姓名 */
    private String realname;


    /** 银行名称 */
    private String bankName;


    /** 图标 */
    private String bankImg;


    /** 银行账号 */
    private String bankAccount;


    /** 开户行地区 */
    private String depositArea;


    /** 开户行名称 */
    private String depositName;


    /** 打款金额 */
    private BigDecimal payToUserCash;


    /** 收款金额 */
    private BigDecimal userGetCash;


    /** 认证状态 */
    private Integer status;


    /** 认证时间 */
    private Timestamp authTime;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    public void copy(ZbBankAuth source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
