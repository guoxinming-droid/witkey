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
* @date 2020-07-28
*/
@Data
public class ZbServiceDto implements Serializable {

    private Integer id;

    /** 标题 */
    private String title;

    /** 描述 */
    private String description;

    /** 价格 */
    private BigDecimal price;

    /** 类型 */
    private Integer type;

    /** 唯一标识 */
    private String identify;

    /** 状态 */
    private Integer status;

    /** 创建时间 */
    private Timestamp createdAt;

    private Timestamp updatedAt;
}
