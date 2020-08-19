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
* @date 2020-07-28
*/
@Data
@TableName("zb_service")
public class ZbService implements Serializable {

    @TableId
    private Integer id;


    /** 标题 */
    @NotBlank
    private String title;


    /** 描述 */
    @NotBlank
    private String description;


    /** 价格 */
    @NotNull
    private BigDecimal price;


    /** 类型 */
    private Integer type;


    /** 唯一标识 */
    private String identify;


    /** 状态 */
    @NotNull
    private Integer status;


    /** 创建时间 */
    private Timestamp createdAt;


    private Timestamp updatedAt;


    public void copy(ZbService source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
