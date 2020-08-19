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
* @date 2020-08-05
*/
@Data
public class ZbEnterpriseAuthDto implements Serializable {

    private Integer id;

    /** 用户编号 */
    private Integer uid;

    /** 公司名称 */
    private String companyName;

    /** 行业末级分类 */
    private Integer cateId;

    /** 员工人数 */
    private Integer employeeNum;

    /** 营业执照 */
    private String businessLicense;

    /** 开始经营时间 */
    private Timestamp beginAt;

    /** 公司网址 */
    private String website;

    /** 省 */
    private Integer province;

    /** 市 */
    private Integer city;

    /** 区 */
    private Integer area;

    /** 公司详细地址 */
    private String address;

    /** 认证状态 0：待验证 1：成功 2：失败 */
    private Integer status;

    /** 认证时间 */
    private Timestamp authTime;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
