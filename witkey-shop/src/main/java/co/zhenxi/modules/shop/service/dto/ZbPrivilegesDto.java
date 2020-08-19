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
public class ZbPrivilegesDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 特权名称 */
    private String title;

    /** 特权描述 */
    private String desc;

    /** 特权编码 */
    private String code;

    /** 排序 */
    private Integer list;

    /** 类型 */
    private Integer type;

    /** 状态 */
    private Integer status;

    /** 推荐状态 */
    private Integer isRecommend;

    /** 特权图标 */
    private String ico;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;

    /** 删除时间 */
    private Timestamp deletedAt;
}
