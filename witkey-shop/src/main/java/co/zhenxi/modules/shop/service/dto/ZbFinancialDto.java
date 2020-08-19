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
* @author Guoxm
* @date 2020-07-16
*/
@Data
public class ZbFinancialDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 收支行为 */
    private Integer action;

    /** 支付渠道类型 */
    private Integer payType;

    /** 支付账号 */
    private String payAccount;

    /** 流水号 */
    private String payCode;

    /** 金额 */
    private BigDecimal cash;

    /** 用户 */
    private Integer uid;

    /** 用户名 */
    private String userName;

    /** 余额 */
    private BigDecimal balance;

    /** 时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
