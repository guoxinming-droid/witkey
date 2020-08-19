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
* @date 2020-07-23
*/
@Data
public class ZbGoodsCommentDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 商品编号 */
    private Integer goodsId;

    /** 用户 */
    private Integer uid;

    /** 商品评价对象 */
    private Integer commentBy;

    /** 速度得分 */
    private Double speedScore;

    /** 质量得分 */
    private Double qualityScore;

    /** 态度得分 */
    private Double attitudeScore;

    /** 评价内容 */
    private String commentDesc;

    /** 评价类型 */
    private Integer type;

    /** 评价时间 */
    private Timestamp createdAt;
}
