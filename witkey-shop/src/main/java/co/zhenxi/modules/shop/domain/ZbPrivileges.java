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
* @author Guoxm
* @date 2020-07-16
*/
@Data
@TableName("zb_privileges")
public class ZbPrivileges implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 特权名称 */
    @NotBlank
    private String title;


    /** 特权描述 */
    @NotBlank
    private String desc;


    /** 特权编码 */
    @NotBlank
    private String code;


    /** 排序 */
    @NotNull
    private Integer list;


    /** 类型 */
    @NotNull
    private Integer type;


    /** 状态 */
    @NotNull
    private Integer status;


    /** 推荐状态 */
    @NotNull
    private Integer isRecommend;


    /** 特权图标 */
    @NotBlank
    private String ico;


    /** 创建时间 */
    @NotNull
    private Timestamp createdAt;


    /** 修改时间 */
    @NotNull
    private Timestamp updatedAt;


    /** 删除时间 */
    private Timestamp deletedAt;


    public void copy(ZbPrivileges source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
