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
public class ZbTaskTemplateDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 模板标题 */
    private String title;

    /** 模板内容 */
    private String content;

    /** 模板类型 */
    private Integer cateId;

    /** 状态 */
    private Integer status;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
