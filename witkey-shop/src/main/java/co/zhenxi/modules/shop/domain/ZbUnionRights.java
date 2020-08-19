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
* @date 2020-07-30
*/
@Data
@TableName("zb_union_rights")
public class ZbUnionRights implements Serializable {

    @TableId
    private Integer id;


    /** 维权类型 */
    @NotNull
    private Integer type;


    /** 对象关联 */
    @NotNull
    private Integer objectId;


    /** 对象类型 */
    private String objectType;

    private String fromName;

    /** 维权描述 */
    @NotBlank
    private String des;


    /** 处理状态 */
    @NotNull
    private Integer status;


    /** 维权人 */
    @NotNull
    private Integer fromUid;


    /** 被维权人 */
    @NotNull
    private Integer toUid;


    /** 后台处理人 */
    @NotNull
    private Integer handelUid;


    /** 维权处理时间 */
    @NotNull
    private Timestamp handledAt;


    /** 软删除  1=>删除  0=>未删除 */
    @NotNull
    private Integer isDelete;


    /** 维权方获得金额 */
    private BigDecimal fromPrice;


    /** 被维权方获得金额 */
    private BigDecimal toPrice;


    /** 维权创建时间 */
    @NotNull
    private Timestamp createdAt;

    private String  cateName;


    public void copy(ZbUnionRights source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
