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
public class ZbRecommendPositionDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 推荐位名称 */
    private String name;

    /** 推荐位别名 */
    private String code;

    /** 推荐位位置描述 */
    private String position;

    /** 推荐位图片 */
    private String pic;

    /** 推荐数量 */
    private Integer num;

    /** 状态 */
    private Integer isOpen;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
