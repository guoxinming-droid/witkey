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
* @date 2020-08-01
*/
@Data
@TableName("zb_sub_order")
public class ZbSubOrder implements Serializable {

    @TableId
    private Integer id;


    /** 子订单标题 */
    @NotBlank
    private String title;


    /** 子订单备注 */
    @NotBlank
    private String note;


    /** 子订单金额 */
    @NotNull
    private Double cash;


    /** 创建用户ID */
    @NotNull
    private Integer uid;


    /** 父订单ID */
    @NotBlank
    private String orderId;


    /** 父订单编号 */
    @NotBlank
    private String orderCode;


    /** 对象ID(TASK,SERVICE,TOOL) */
    @NotNull
    private Integer productId;


    /** 对象类型:1:TASK,2:SERVICE,3:TOOL */
    @NotNull
    private Integer productType;


    /** 子订单状态 */
    @NotNull
    private Integer status;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    public void copy(ZbSubOrder source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
