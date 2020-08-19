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
@TableName("zb_task_template")
public class ZbTaskTemplate implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 模板标题 */
    @NotBlank
    private String title;


    /** 模板内容 */
    @NotBlank
    private String content;


    /** 模板类型 */
    @NotNull
    private Integer cateId;


    /** 状态 */
    @NotNull
    private Integer status;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    public void copy(ZbTaskTemplate source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
