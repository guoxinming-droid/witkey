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
* @date 2020-07-28
*/
@Data
public class ZbEmployGoodsDto implements Serializable {

    private Integer id;

    /** 关联雇佣id */
    private Integer employId;

    /** 关联服务id */
    private Integer serviceId;

    /** 关联创建时间 */
    private Timestamp createdAt;
}
