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
* @date 2020-07-30
*/
@Data
public class ZbSuccessCaseDto implements Serializable {

    private Integer id;

    /** 创建用户ID */
    private Integer uid;

    /** 发布人name */
    private String username;

    /** 成功案例标题 */
    private String title;

    /** 成功案例描述 */
    private String des;

    /** 成功案例跳转链接 */
    private String url;

    /** 成功案例封面 */
    private String pic;

    /** 成功案例分类 */
    private Integer cateId;

    /** 成功案例发布方式: 0->平台 1->用户 */
    private Integer type;

    /** 发布者id */
    private Integer pubUid;

    /** 访问次数 */
    private Integer viewCount;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 金额 */
    private BigDecimal cash;

    private String cateName;
}
