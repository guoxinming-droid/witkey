/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbFinancial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Repository
@Mapper
public interface ZbFinancialMapper extends CoreMapper<ZbFinancial> {

    @Select(" SELECT  SUM(cash) AS cash, created_at FROM  zb_financial where action in (3,4) AND created_at BETWEEN #{startTime} AND #{endTime} GROUP BY action,created_at  ")
    List<ZbFinancial> financeStatement(String startTime, String endTime);


    @Select("SELECT\n" +
            "  o.id,\n" +
            "\to.code,\n" +
            "\to.title, \n" +
            "\to.uid, \n" +
            "\to.cash,\n" +
            "\to.status, \n" +
            "\to.invoice_status,\n" +
            "\to.note,\n" +
            "\to.created_at,\n" +
            "\tu.name AS userName\n" +
            "FROM\n" +
            "\tzb_order AS o\n" +
            "LEFT JOIN \tzb_users AS u ON o.uid = u.id\n" +
            "WHERE\n" +
            "\to.task_id <> '' \n" +
            "\tAND o.status = 0 \n" +
            "  AND o.code = #{code}\n" +
            "  AND o.created_at >= #{startTime}\t\n" +
            "\tAND o.created_at <= #{endTime} ORDER BY o.id DESC ")
    List<ZbFinancial> rechargeList(String code ,String userName,String startTime,String endTime);

    @Select("select id, action, pay_type, pay_account, pay_code, cash, uid, created_at from zb_financial  ${whereSql}")
    List<ZbFinancial> financeList(String whereSql);

    @Select("select f.id, f.action, f.pay_type, f.pay_account, f.pay_code, f.cash, f.uid,   ud.balance,u.name AS userName, f.created_at from zb_financial  AS f \n" +
            "LEFT JOIN zb_user_detail AS  ud  ON f.uid = ud.uid\n" +
            "LEFT JOIN zb_users AS u ON f.uid = u.id  \n" +
            " ${whereSql} \n" +
            "ORDER BY f.created_at DESC")
    List<ZbFinancial> userFinance(@Param("whereSql") String whereSql);

}
