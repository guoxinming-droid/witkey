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
@TableName("zb_task_rights")
public class ZbTaskRights implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 维权角色 */
    @NotNull
    private Integer role;


    /** 维权类型 */
    @NotNull
    private Integer type;


    /** 维权任务 */
    @NotNull
    private Integer taskId;


    /** 投稿记录 */
    @NotNull
    private Integer workId;


    /** 维权描述 */
    @NotBlank
    private String desc;


    /** 状态 */
    @NotNull
    private Integer status;


    /** 维权人 */
    @NotNull
    private Integer fromUid;


    /** 被维权人 */
    @NotNull
    private Integer toUid;


    /** 处理人 */
    @NotNull
    private Integer handleUid;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 处理时间 */
    private Timestamp handledAt;


    public void copy(ZbTaskRights source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
