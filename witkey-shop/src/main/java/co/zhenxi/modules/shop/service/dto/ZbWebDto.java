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
* @author Guo xinming
* @date 2020-07-16
*/
@Data
public class ZbWebDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 名称 */
    private String name;

    /** 活动链接 */
    private String url;

    /** 排序 */
    private Integer sort;

    /** 栏目分类 */
    private Integer webCateId;

    /** 状态 */
    private Integer status;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
