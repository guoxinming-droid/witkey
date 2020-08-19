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
* @date 2020-07-31
*/
@Data
@TableName("zb_activity")
public class ZbActivity implements Serializable {

    /** id */
    @TableId
    private Integer id;


    /** 活动标题 */
    private String title;


    /** 活动封面 */
    private String pic;


    /** 活动链接 */
    private String url;


    /** 排序 */
    @NotNull
    private Integer sort;


    /** 状态 0：未发布 1：已发布 */
    @NotNull
    private Integer status;


    /** 发布时间 */
    private Timestamp pubAt;


    /** 简介 */
    private String des;


    private Timestamp createdAt;


    private Timestamp updatedAt;


    public void copy(ZbActivity source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
