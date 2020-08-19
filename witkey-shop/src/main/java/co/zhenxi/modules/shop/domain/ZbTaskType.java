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
* @date 2020-07-22
*/
@Data
@TableName("zb_task_type")
public class ZbTaskType implements Serializable {

    @TableId
    private Integer id;


    /** 类型名称 */
    @NotBlank
    private String name;


    /** 状态 */
    @NotNull
    private Integer status;


    /** 别名 */
    private String alias;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 任务类型描述 */
    @NotBlank
    private String des;


    public void copy(ZbTaskType source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
