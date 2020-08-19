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
* @date 2020-08-19
*/
@Data
public class ZbShopFocusDto implements Serializable {

    /** 店铺关注关联序号 */
    private Integer id;

    /** 关注者id */
    private Integer uid;

    /** 店铺id */
    private Integer shopId;

    private Timestamp createdAt;
}
