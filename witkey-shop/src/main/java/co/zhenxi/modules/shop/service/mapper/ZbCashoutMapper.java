/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbCashout;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-08-01
*/
@Repository
@Mapper
public interface ZbCashoutMapper extends CoreMapper<ZbCashout> {

    @Select("select * from zb_cashout ")
    List<ZbCashout> financeWithdraw(@Param("ew") Wrapper<ZbCashout> queryWrapper);

    @Select(" SELECT  \n" +
            "     id,\n" +
            "\t\t cash,\n" +
            "\t\t real_cash,\n" +
            "\t\t fees,\n" +
            "\t\t created_at,\n" +
            "\t\t (SELECT name FROM zb_users) AS userName\n" +
            "  FROM zb_cashout\n" +
            "\tWHERE status = '1' \n" +
            "   AND  created_at BETWEEN #{start} AND #{end} \n" +
            "\t ORDER BY created_at DESC ")
    List<ZbCashout> financeProfit(String start, String end);

    @Select("SELECT\n" +
            "  c.id,\n" +
            "\tc.uid,\n" +
            "\tu.name AS userName,\n" +
            "\tc.pay_type,\n" +
            "\tc.pay_account,\n" +
            "\tc.pay_code,\n" +
            "\tc.cash,\n" +
            "\tc.fees,\n" +
            "\tc.real_cash,\n" +
            "\tc.admin_uid,\n" +
            "\tc.cashout_type,\n" +
            "\tc.cashout_account,\n" +
            "\tc.STATUS,\n" +
            "\tc.note \n" +
            "FROM\n" +
            "\tzb_cashout AS c\n" +
            "\tLEFT JOIN zb_users AS u ON c.uid = u.id\n" +
            "WHERE u.name = #{userName}\n" +
            "  AND c.cashout_type = #{cashoutType}\n" +
            "\tAND c.created_at >= #{startTime}\n" +
            "\tAND c.created_at <= #{endTime}")
    List<ZbCashout> cashoutList(String cashoutType, String userName, String startTime, String endTime);

    @Select("SELECT\n" +
            "\tzbu.NAME NAME,\n" +
            "\tzbc.cash cash,\n" +
            "\tifnull(zbc.created_at,NOW()) time \n" +
            "FROM\n" +
            "\tzb_cashout zbc,\n" +
            "\tzb_users zbu \n" +
            "WHERE\n" +
            "\tzbc.uid = zbu.id \n" +
            "ORDER BY\n" +
            "\tzbc.created_at DESC")
    Page<Map<String, Object>> getCashout();
}
