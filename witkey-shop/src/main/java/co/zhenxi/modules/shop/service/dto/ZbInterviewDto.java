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
* @author guoke
* @date 2020-07-27
*/
@Data
public class ZbInterviewDto implements Serializable {

    private Integer id;

    /** 访谈标题 */
    private String title;

    /** 用户编号 */
    private Integer uid;

    /** 用户名 */
    private String username;

    /** 店铺编号 */
    private Integer shopId;

    /** 店铺名 */
    private String shopName;

    /** 店铺封面 */
    private String shopCover;

    /** 访谈内容 */
    private String desc;

    /** 浏览数 */
    private Integer viewCount;

    /** 排序 倒序 */
    private Integer list;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
