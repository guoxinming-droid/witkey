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
* @author Guo
* @date 2020-07-21
*/
@Data
@TableName("zb_fast_task")
public class ZbFastTask implements Serializable {

    /** 任务编号 */
    @TableId
    private Integer id;


    /** 任务标题 */
    private String title;


    /** 用户名称 */
    private Integer uid;


    /** 任务ID */
    @NotNull
    private Integer taskId;


    /** 发布状态 */
    @NotNull
    private Integer status;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    /** 项目类型 */
    @NotNull
    private Integer taskType;


    /** 手机号 */
    private String mobile;


    /** 描述 */
    private String des;


    public void copy(ZbFastTask source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
