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
import java.util.List;
import java.util.Map;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Data
@TableName("zb_shop")
public class ZbShop implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 店主 */
    @NotNull
    private Integer uid;


    /** 店铺类型 */
    @NotNull
    private Integer type;


    /** 店铺封面 */
    @NotBlank
    private String shopPic;


    /** 店铺名称 */
    @NotBlank
    private String shopName;



    private String uname;

    /** 店铺介绍 */
    @NotBlank
    private String shopDesc;


    /** 省 */
    @NotNull
    private Integer province;


    /** 市 */
    @NotNull
    private Integer city;


    /** 店铺状态 */
    @NotNull
    private Integer status;


    /** 评价数 */
    private Integer totalComment;


    /** 好评数 */
    private Integer goodComment;


    /** 店铺背景图 */
    private String shopBg;


    /** seo标题 */
    private String seoTitle;


    /** seo关键词 */
    private String seoKeyword;


    /** seo描述 */
    private String seoDesc;


    /** 是否推荐 */
    @NotNull
    private Integer isRecommend;


    /** 导航配置 */
    @NotBlank
    private String navRules;


    /** 导航肤色 */
    @NotBlank
    private String navColor;


    /** 轮播图 */
    @NotBlank
    private String bannerRules;


    /** 中部广告 */
    @NotBlank
    private String centralAd;



    /** 底部广告 */
    @NotBlank
    private String footerAd;


    /** 创建时间 */
    @NotNull
    private Timestamp createdAt;


    /** 修改时间 */
    @NotNull
    private Timestamp updatedAt;


    /** 一级标签ID*/
    private String catePid;

    /** 二级标签ID*/
    private String cateSid;


    /** 二级标签名称*/
    private String tagName;

    /** 一级标签名称*/
    private String tagPname;


    private Map<String ,Object> authenticationMap;

    private Integer serviceCount;


    public void copy(ZbShop source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
