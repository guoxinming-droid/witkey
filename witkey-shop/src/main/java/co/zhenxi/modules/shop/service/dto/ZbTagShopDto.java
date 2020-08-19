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
* @author guoke
* @date 2020-08-15
*/
@Data
public class ZbTagShopDto implements Serializable {

    private Integer id;

    /** kppw_skill_tags表的主键id */
    private Integer tagId;

    /** kppw_shop表的主键id即店铺id */
    private Integer shopId;
}
