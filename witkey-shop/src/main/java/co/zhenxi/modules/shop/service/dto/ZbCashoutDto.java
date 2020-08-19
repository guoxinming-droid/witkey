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
* @date 2020-08-01
*/
@Data
public class ZbCashoutDto implements Serializable {

    private Integer id;

    /** 提现用户ID */
    private Integer uid;

    /** 用户名 */
    private String userName;

    /** 平台支付渠道类型 */
    private Integer payType;

    /** 平台支付账号 */
    private String payAccount;

    /** 平台支付渠道流水号 */
    private String payCode;

    /** 提现金额 */
    private BigDecimal cash;

    /** 提现手续费 */
    private BigDecimal fees;

    /** 用户提现真实金额 */
    private BigDecimal realCash;

    /** 管理员账号 */
    private Integer adminUid;

    /** 提现类型 1：支付宝 2：银行卡 */
    private Integer cashoutType;

    /** 提现账号 */
    private String cashoutAccount;

    private Integer status;

    /** 提现备注 */
    private String note;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
