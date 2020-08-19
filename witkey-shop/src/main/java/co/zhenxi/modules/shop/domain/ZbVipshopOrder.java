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
* @date 2020-07-27
*/
@Data
@TableName("zb_vipshop_order")
public class ZbVipshopOrder implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 订单编号 */
    @NotBlank
    private String code;


    /** 订单标题 */
    @NotBlank
    private String title;


    /** 用户名 */
    @NotNull
    private Integer uid;


    /** 套餐 */
    @NotNull
    private Integer packageId;


    /** 店铺 */
    @NotNull
    private Integer shopId;


    /** 套餐时长(月) */
    @NotNull
    private Integer timePeriod;


    /** 金额 */
    @NotNull
    private BigDecimal cash;


    /** 订单状态 */
    @NotNull
    private Integer status;


    /** 创建时间 */
    @NotNull
    private Timestamp createdAt;


    /** 修改时间 */
    @NotNull
    private Timestamp updatedAt;


    public void copy(ZbVipshopOrder source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
