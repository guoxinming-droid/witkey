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
public class ZbRecommendDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 推荐位 */
    private Integer positionId;

    /** 类型 */
    private String type;

    /** 推荐编号 */
    private Integer recommendId;

    /** 推荐类型 */
    private String recommendType;

    /** 推荐名称 */
    private String recommendName;

    /** 推荐图片 */
    private String recommendPic;

    /** 跳转链接 */
    private String url;

    /** 开始时间 */
    private Timestamp startTime;

    /** 结束时间 */
    private Timestamp endTime;

    /** 排序 */
    private Integer sort;

    /** 是否开启 1-开启 2-关闭 3-删除 */
    private Integer isOpen;

    /** 创建时间 */
    private Timestamp createdAt;
}
