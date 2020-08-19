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
* @date 2020-07-22
*/
@Data
public class ZbTaskTypeDto implements Serializable {

    private Integer id;

    /** 类型名称 */
    private String name;

    /** 状态 */
    private Integer status;

    /** 别名 */
    private String alias;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 任务类型描述 */
    private String des;
}
