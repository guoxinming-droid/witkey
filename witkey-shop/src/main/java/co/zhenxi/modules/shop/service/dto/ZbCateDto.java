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
* @date 2020-07-20
*/
@Data
public class ZbCateDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 分类名称 */
    private String name;
    /** 分类名称 */
    private String pname;

    /** 父级分类 */
    private Integer pid;

    /** 分类路径 */
    private String path;

    /** 分类图标 */
    private String pic;

    /** 排序 */
    private Integer sort;

    /** 点击量 */
    private Integer chooseNum;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
