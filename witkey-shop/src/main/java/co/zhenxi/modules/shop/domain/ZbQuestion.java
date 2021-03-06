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
* @date 2020-08-06
*/
@Data
@TableName("zb_question")
public class ZbQuestion implements Serializable {

    /** 问题列表自增id */
    @TableId
    private Integer id;


    /** 问题浏览次数 */
    @NotNull
    private Integer num;


    /** 问题的描述 */
    private String discription;


    /** 问题是否解决 1表示发布 2表示审核通过 3表示已经回答 4表示问题解决 5表示审核失败 */
    @NotNull
    private Integer status;


    /** 提问者uid */
    @NotNull
    private Integer uid;

    private String userName;

    /** 提问时间 */
    @NotNull
    private Timestamp time;


    /** 审核时间 */
    @NotNull
    private Timestamp verifyAt;


    /** 问题类别 */
    @NotNull
    private Integer category;


    /** 问题被回答次数 */
    @NotNull
    private Integer answernum;


    /** 点赞次数 */
    @NotNull
    private Integer praisenum;


    public void copy(ZbQuestion source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
