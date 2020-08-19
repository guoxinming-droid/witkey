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
* @author guoke
* @date 2020-08-01
*/
@Data
@TableName("zb_cashout")
public class ZbCashout implements Serializable {

    @TableId
    private Integer id;


    /** 提现用户ID */
    @NotNull
    private Integer uid;


    /** 平台支付渠道类型 */
    @NotNull
    private Integer payType;


    /** 平台支付账号 */
    @NotBlank
    private String payAccount;


    /** 平台支付渠道流水号 */
    @NotBlank
    private String payCode;


    /** 提现金额 */
    @NotNull
    private BigDecimal cash;


    /** 提现手续费 */
    @NotNull
    private BigDecimal fees;


    /** 用户提现真实金额 */
    private BigDecimal realCash;


    /** 管理员账号 */
    @NotNull
    private Integer adminUid;


    /** 提现类型 1：支付宝 2：银行卡 */
    @NotNull
    private Integer cashoutType;


    /** 提现账号 */
    @NotBlank
    private String cashoutAccount;


    @NotNull
    private Integer status;


    /** 提现备注 */
    private String note;


    private Timestamp createdAt;


    private Timestamp updatedAt;


    public void copy(ZbCashout source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
