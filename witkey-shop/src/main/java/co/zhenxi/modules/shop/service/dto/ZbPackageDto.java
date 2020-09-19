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
public class ZbPackageDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 套餐名称 */
    private String title;

    /** 套餐logo */
    private String logo;

    /** 套餐状态 */
    private Integer status;

    /** 价格配置规则 */
    private String priceRules;

    /** 排序 */
    private Integer list;

    /** 套餐类型 */
    private Integer type;

    /** 套餐状态 */
    private Integer typeStatus;

    /** 套餐价格*/
    private double price;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;

    /** 删除时间 */
    private Timestamp deletedAt;
}
