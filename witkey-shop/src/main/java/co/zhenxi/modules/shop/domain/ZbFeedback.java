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
* @author Guo xinming
* @date 2020-07-16
*/
@Data
@TableName("zb_feedback")
public class ZbFeedback implements Serializable {

    @TableId
    private Integer id;


    /** 用户 */
    @NotNull
    private Integer uid;


    /** 用户手机 */
    @NotBlank
    private String phone;


    /** 反馈内容 */
    private String desc;


    /** 用户反馈类型 */
    @NotNull
    private Integer type;


    /** 回复状态 */
    @NotNull
    private Integer status;


    /** 回复内容 */
    private String replay;


    /** 反馈时间 */
    private Timestamp createdTime;


    /** 反馈处理时间 */
    private Timestamp handleTime;


    public void copy(ZbFeedback source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
