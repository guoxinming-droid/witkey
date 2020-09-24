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
* @date 2020-07-23
*/
@Data
@TableName("zb_work")
public class ZbWork implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 任务ID */
    @NotNull
    private Integer taskId;


    /** 被关注者id */
    @NotBlank
    private String descs;


    /** 状态 */
    @NotNull
    private Integer status;


    /** 是否禁用稿件 */
    @NotNull
    private Integer forbidden;


    /** 威客人员 */
    @NotNull
    private Integer uid;


    /** 中标选中对象 */
    @NotNull
    private Integer bidBy;


    /** 中标时间 */
    private Timestamp bidAt;


    /** 稿件创建时间 */
    private Timestamp createdAt;


    /** 威客报价金额 */
    private BigDecimal price;


    public void copy(ZbWork source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
