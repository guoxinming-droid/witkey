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
public class ZbUserFocusDto implements Serializable {

    private Integer id;

    /** 用户id */
    private Integer uid;

    /** 被关注者id */
    private Integer focusUid;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
