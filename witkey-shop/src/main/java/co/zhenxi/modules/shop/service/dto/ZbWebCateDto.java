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
public class ZbWebCateDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 分类名称 */
    private String name;

    /** 排序 */
    private Integer sort;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
