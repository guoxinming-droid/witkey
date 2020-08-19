/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author Gxm
* @date 2020-07-16
*/
@Data
@Getter
@Setter
public class ZbAgreementDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 协议名称 */
    private String name;

    /** 协议内容 */
    private String content;

    /** 名称代号 */
    private String codeName;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
