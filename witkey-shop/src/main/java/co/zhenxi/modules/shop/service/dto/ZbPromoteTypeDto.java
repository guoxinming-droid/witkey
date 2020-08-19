/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author guoke
* @date 2020-08-06
*/
@Data
public class ZbPromoteTypeDto implements Serializable {

    /** 推广类型列表自增id */
    private Integer id;

    /** 推广类型名称 */
    private String name;

    /** 名称代号 （拼音大写） */
    private String codeName;

    /** 推广类型  1=>注册推广 */
    private Integer type;

    /** 推广金额 */
    private BigDecimal price;

    /** 完成推广的条件  1=>实名认证  2=>邮箱认证 3=>支付认证 */
    private Integer finishConditions;

    /** 是否开启 1=>开启 2=>关闭 */
    private Integer isOpen;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
