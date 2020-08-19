/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Data
public class ZbShopDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 店主 */
    private Integer uid;

    /** 店铺类型 */
    private Integer type;

    /** 店铺封面 */
    private String shopPic;

    /** 店铺名称 */
    private String shopName;

    /** 店铺介绍 */
    private String shopDesc;

    /** 省 */
    private Integer province;

    /** 市 */
    private Integer city;

    /** 店铺状态 */
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
    private Integer isRecommend;

    /** 导航配置 */
    private String navRules;

    /** 导航肤色 */
    private String navColor;

    /** 轮播图 */
    private String bannerRules;

    /** 中部广告 */
    private String centralAd;

    /** 底部广告 */
    private String footerAd;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
