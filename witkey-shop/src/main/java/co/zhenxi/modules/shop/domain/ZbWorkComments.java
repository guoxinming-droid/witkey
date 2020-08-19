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
* @date 2020-07-23
*/
@Data
@TableName("zb_work_comments")
public class ZbWorkComments implements Serializable {

    @TableId
    private Integer id;


    /** 稿件ID */
    @NotNull
    private Integer workId;


    /** 评论内容 */
    @NotBlank
    private String comment;


    /** 评论人ID */
    @NotNull
    private Integer uid;


    /** 昵称 */
    @NotBlank
    private String nickname;


    /** 所属任务ID */
    @NotNull
    private Integer taskId;


    /** 父级评论ID */
    @NotNull
    private Integer pid;


    /** 创建时间 */
    private Timestamp createdAt;


    public void copy(ZbWorkComments source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
