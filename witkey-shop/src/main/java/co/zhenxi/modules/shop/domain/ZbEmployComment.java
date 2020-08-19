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
@TableName("zb_employ_comment")
public class ZbEmployComment implements Serializable {

    @TableId
    private Integer id;


    @NotNull
    private Integer employId;


    @NotNull
    private Integer fromUid;


    @NotNull
    private Integer toUid;


    private String comment;


    /** 评价对象 */
    private Integer commentBy;


    /** 速度分数 1-5 */
    private Integer speedScore;


    /** 质量分数 1-5 */
    private Integer qualityScore;


    /** 态度分数 1-5 */
    private Integer attitudeScore;


    /** 评价类型 1表示好评 2表示中评 3表示差评 */
    private Integer type;


    /** 创建时间 */
    @NotNull
    private Timestamp createdAt;


    public void copy(ZbEmployComment source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
