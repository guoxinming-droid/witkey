/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author Guoxm
* @date 2020-07-17
*/
@Data
public class ZbConfigDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 配置别名 */
    private String alias;

    /** 配置规则 */
    private String rule;

    /** 配置类型 */
    private String type;

    /** 配置名称 */
    private String title;

    /** 配置描述 */
    private String des;
}
