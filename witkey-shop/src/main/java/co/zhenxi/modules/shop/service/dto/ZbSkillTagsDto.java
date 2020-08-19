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
* @date 2020-08-15
*/
@Data
public class ZbSkillTagsDto implements Serializable {

    private Integer id;

    /** 技能标签 */
    private String tagName;

    /** 关联的任务类型 */
    private Integer cateId;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
