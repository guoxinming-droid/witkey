/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
* @author guoke
* @date 2020-07-30
*/
@Data
public class ZbArticleCategoryDto implements Serializable {

    /** 文章分类编号 */
    private Integer id;

    /** 文章分类父编号 */
    private Integer pid;

    /** 文章分类父编号 */
    private List<Object> pids;

    /** 分类名称 */
    private String cateName;

    /** 文章数量 */
    private Integer articles;

    /** 排序 */
    private Integer displayOrder;

    /** 链接地址 */
    private String url;

    /** 用户编号 */
    private Integer userId;

    /** 用户名 */
    private String userName;

    /** SEO描述 */
    private String description;

    /** SEO标题 */
    private String seotitle;

    /** SEO关键词 */
    private String keyword;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
