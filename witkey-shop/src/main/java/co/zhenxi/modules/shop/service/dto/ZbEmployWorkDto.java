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
* @date 2020-08-21
*/
@Data
public class ZbEmployWorkDto implements Serializable {

    private Integer id;

    /** 稿件描述 */
    private String desc;

    /** 稿件id */
    private Integer employId;

    /** 状态 0表示没有验收 1表示验收 */
    private Integer status;

    /** 交稿威客id */
    private Integer uid;

    /** 文件后缀 */
    private String type;

    /** 创建时间 */
    private Timestamp createdAt;
}
