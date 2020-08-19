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

import java.io.Serializable;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Getter
@Setter
@Data
public class ZbAdTargetDto implements Serializable {

    /** 广告位编号 */
    private Integer targetId;

    /** 广告位名称 */
    private String name;

    /** 广告位标签 */
    private String code;

    /** 描述 */
    private String description;

    /** 广告标签 */
    private String targets;

    /** 广告位置 */
    private String position;

    /** 广告位大小 */
    private String adSize;

    /** 广告位个数 */
    private Integer adNum;

    /** 广告位图片 */
    private String pic;

    /** 是否开启 */
    private Integer isOpen;

    /** 内容 */
    private String content;
}
