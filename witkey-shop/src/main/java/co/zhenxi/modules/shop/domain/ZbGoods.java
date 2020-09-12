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
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author guoke
* @date 2020-07-29
*/
@Data
@TableName("zb_goods")
@EqualsAndHashCode
public class ZbGoods implements Serializable {


    /**
     * types 属性我放到子类里面了 要不sql报错
     *  有用到 types字段的 可以用 子类接收
     */


    @TableId
    private Integer id;


    @NotNull
    private Integer uid;


    @NotNull
    private Integer shopId;


    /** 商品二级分类编号 */
    @NotNull
    private Integer cateId;


    /** 商品标题 */
    @NotBlank
    private String title;


    /** 商品价格单位 0：每件 1：每时 */
    @NotNull
    private Integer unit;


    /** 商品类型 1：作品 2：服务 */
    @NotNull
    private Integer type;



    @NotNull
    private BigDecimal cash;

    private Integer catePid;


    /** 商品封面 */
    private String cover;


    /** 商品状态 0：未审核 1：审核通过上架了  2：审核通过下架了  3：审核失败 */
    @NotNull
    private Integer status;


    /** 增值工具过期时间 */
    @NotNull
    private Timestamp toolExpirationTime;


    /** 是否推荐到商城 0：不推荐 1：推荐 */
    @NotNull
    private Integer isRecommend;


    /** 推荐到商城截止时间 */
    private Timestamp recommendEnd;


    /** 卖出数量 */
    @NotNull
    private Integer salesNum;


    /** 总评价数 */
    @NotNull
    private Integer commentsNum;


    /** 好评数 */
    @NotNull
    private Integer goodComment;


    /** 访问量 */
    @NotNull
    private Integer viewNum;


    /** 用户软删除 0表示未删除 1表示删除 */
    @NotNull
    private Integer isDelete;


    /** 审核不通过原因 */
    @NotBlank
    private String recommendText;


    @NotBlank
    private String seoTitle;


    @NotBlank
    private String seoKeyword;


    @NotBlank
    private String seoDesc;


    @NotNull
    private Timestamp createdAt;


    @NotNull
    private Timestamp updatedAt;


    /** 商品描述 */
    @NotBlank
    private String des;




    public void copy(ZbGoods source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
