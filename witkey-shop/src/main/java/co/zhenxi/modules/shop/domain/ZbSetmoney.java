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
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author Guo
* @date 2020-07-20
*/
@Data
@TableName("zb_setmoney")
public class ZbSetmoney implements Serializable {

    @TableId
    private Integer id;


    /** 用户最小充值金额 */
    private BigDecimal rechargeMinAmount;


    /** 用户最小提现金额 */
    private BigDecimal withdrawalMinAmount;


    /** 用户当天提现最大金额 */
    private BigDecimal daywithdrawalMinAmount;


    /** 单笔资费 */
    private Double singleCharge;


    /** 单笔最低收费 */
    private Double singleMinimumCharge;


    /** 单笔最高收费 */
    private BigDecimal maximumSingleCharge;


    public void copy(ZbSetmoney source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
