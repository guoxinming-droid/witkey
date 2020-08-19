/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-08-01
*/
@Repository
@Mapper
public interface ZbOrderMapper extends CoreMapper<ZbOrder> {

    @Select("SELECT\n" +
            "\tid,\n" +
            "\t'任务佣金' AS froms,\n" +
            "\ttask_id,\n" +
            "\t(select name from zb_users where id = uid) AS userName,\n" +
            "\tcash,\n" +
            "\tcreated_at \n" +
            "FROM\n" +
            "\tzb_order  where status = 1 AND task_id <> '' AND created_at BETWEEN #{start} AND  #{end} ORDER BY  created_at  DESC " )
    List<ZbOrder> financeProfit( String start, String end );

    @Select("SELECT\n" +
            "\tSUM( cash ) AS cash,\n" +
            "\tcreated_at \n" +
            "FROM\n" +
            "\tzb_order \n" +
            "WHERE\n" +
            "      task_id <> ''\n" +
            "  AND created_at BETWEEN '2020-07-27' \n" +
            "\tAND '2020-08-02' \n" +
            "GROUP BY\n" +
            "\tcreated_at")
    List<ZbOrder> financeStatement(String startTime, String endTime);


    @Select("select * from zb_order where code = #{code}")
    ZbOrder getByCode(String code);

}
