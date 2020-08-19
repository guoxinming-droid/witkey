/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.domain;
import co.zhenxi.tools.domain.LocalStorage;
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
* @date 2020-07-22
*/
@Data
@TableName("zb_task_attachment")
public class ZbTaskAttachment implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 任务ID */
    @NotNull
    private Integer taskId;


    /** 附件ID */
    @NotNull
    private Integer attachmentId;


    private Timestamp createdAt;


    private Timestamp updatedAt;


    private LocalStorage localStorage;


    public void copy(ZbTaskAttachment source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
