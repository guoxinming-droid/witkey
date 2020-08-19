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
* @author Guoxm
* @date 2020-07-16
*/
@Data
@TableName("zb_financial")
public class ZbFinancial implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 收支行为 */
    @NotNull
    private Integer action;


    /** 支付渠道类型 */
    private Integer payType;


    /** 支付账号 */
    private String payAccount;


    /** 流水号 */
    private String payCode;


    /** 金额 */
    @NotNull
    private BigDecimal cash;


    /** 用户 */
    private Integer uid;


    /** 时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    /** 用户名 */
    private String userName;

    /** 余额 */
    private BigDecimal balance;

    public void copy(ZbFinancial source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
