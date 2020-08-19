/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.domain;
import com.baomidou.mybatisplus.annotation.TableField;
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
* @date 2020-07-30
*/
@Data
@TableName("zb_shop_order")
public class ZbShopOrder implements Serializable {

    @TableId
    private Integer id;


    /** 订单号 */
    @NotBlank
    private String code;


    /** 订单标题 */
    @NotBlank
    private String title;


    /** 用户编号 */
    @NotNull
    private Integer uid;


    /** 对象编号 */
    @NotNull
    private Integer objectId;


    /** 对象类型 */
    @NotNull
    private Integer objectType;


    @NotNull
    private BigDecimal cash;


    /** 订单状态 */
    @NotNull
    private Integer status;


    private Integer invoiceStatus;


    private String note;


    /** 创建订单时间 */
    @NotNull
    private Timestamp createdAt;


    /** 支付时间 */
    private Timestamp payTime;


    /** 交易提成比例 */
    @NotNull
    private Double tradeRate;


    /** 确认文件时间 */
    @NotNull
    private Timestamp confirmTime;


    @TableField(exist = false)
    private ZbUsers zbUsers;


    public void copy(ZbShopOrder source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
