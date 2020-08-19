/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author guoke
* @date 2020-07-29
*/
@Data
public class ZbGoodsDto implements Serializable {

    private Integer id;

    private Integer uid;

    private Integer shopId;

    /** 商品二级分类编号 */
    private Integer cateId;

    /** 商品标题 */
    private String title;

    /** 商品价格单位 0：每件 1：每时 */
    private Integer unit;

    /** 商品类型 1：作品 2：服务 */
    private Integer type;

    private String types;

    private BigDecimal cash;

    /** 商品封面 */
    private String cover;

    /** 商品状态 0：未审核 1：审核通过上架了  2：审核通过下架了  3：审核失败 */
    private Integer status;

    /** 增值工具过期时间 */
    private Timestamp toolExpirationTime;

    /** 是否推荐到商城 0：不推荐 1：推荐 */
    private Integer isRecommend;

    /** 推荐到商城截止时间 */
    private Timestamp recommendEnd;

    /** 卖出数量 */
    private Integer salesNum;

    /** 总评价数 */
    private Integer commentsNum;

    /** 好评数 */
    private Integer goodComment;

    /** 访问量 */
    private Integer viewNum;

    /** 用户软删除 0表示未删除 1表示删除 */
    private Integer isDelete;

    /** 审核不通过原因 */
    private String recommendText;

    private String seoTitle;

    private String seoKeyword;

    private String seoDesc;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    /** 商品描述 */
    private String des;
}
