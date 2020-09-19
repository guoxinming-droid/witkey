/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.domain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author guoke
* @date 2020-07-27
*/
@Data
@TableName("zb_interview")
public class ZbInterview implements Serializable {

    @TableId
    private Integer id;


    /** 访谈标题 */
    @NotBlank
    private String title;


    /** 用户编号 */
    @NotNull
    private Integer uid;


    /** 用户名 */
    @NotBlank
    private String username;


    /** 店铺编号 */
    @NotNull
    private Integer shopId;


    /** 店铺名 */
    @NotBlank
    private String shopName;


    /** 店铺封面 */
    @NotBlank
    private String shopCover;


    /** 访谈内容 */
    @NotBlank
    private String desc;


    /** 浏览数 */
    @NotNull
    private Integer viewCount;


    /** 排序 倒序 */
    @NotNull
    private Integer list;


    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp createdAt;


    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp updatedAt;


    public void copy(ZbInterview source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
