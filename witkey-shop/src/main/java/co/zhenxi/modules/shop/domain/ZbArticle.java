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
* @date 2020-07-30
*/
@Data
@TableName("zb_article")
public class ZbArticle implements Serializable {

    /** 文章编号 */
    @TableId
    private Integer id;


    /** 分类编号 */
    @NotNull
    private Integer catId;


    /** 用户编号 */
    @NotNull
    private Integer userId;


    /** 用户名 */
    private String userName;


    /** 标题 */
    @NotBlank
    private String title;


    /** 作者 */
    private String author;


    /** 来源 */
    private String froms;


    /** 来源地址 */
    private String fromurl;


    /** 文章地址 */
    private String url;


    /** 简介 */
    private String summary;


    /** 新闻列表图片 */
    private String pic;


    @NotNull
    private Integer thumb;


    @NotNull
    private Integer tag;


    @NotNull
    private Integer status;


    /** 文字内容 */
    private String content;


    /** 文章阅读浏览次数 */
    private Integer viewTimes;


    /** SEO标题 */
    private String seotitle;


    /** SEO关键词 */
    private String keywords;


    /** SEO描述 */
    private String description;


    /** 排序 */
    private Integer displayOrder;


    /** 是否推荐 1->是 2->否 */
    private Integer isRecommended;


    /** 添加时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    public void copy(ZbArticle source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
