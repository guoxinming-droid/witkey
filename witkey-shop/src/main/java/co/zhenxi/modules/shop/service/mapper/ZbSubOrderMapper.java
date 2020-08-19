/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbSubOrder;
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
public interface ZbSubOrderMapper extends CoreMapper<ZbSubOrder> {
    @Select("\t     SELECT   \n" +
            "\t          id,\n" +
            "\t         '增值服务' AS froms,\n" +
            "            (SELECT name FROM zb_users WHERE id = uid ) AS userName,\n" +
            "            cash,\n" +
            "            created_at\n" +
            "            FROM\n" +
            "            zb_sub_order \n" +
            "\t\t\twhere product_type = '3' \n" +
            "\t\t\tAND   status = '1' \n" +
            "\t\t\tAND   created_at BETWEEN #{start} AND  #{end} \n" +
            "\t\t\tORDER BY  created_at  DESC ")
    List<ZbSubOrder> financeProfit(String start, String end);


    @Select("SELECT\n" +
            "\tSUM( cash ) AS cash,\n" +
            "\tcreated_at \n" +
            "FROM\n" +
            "\tzb_sub_order \n" +
            "WHERE\n" +
            "      product_type = 3\n" +
            "  AND created_at BETWEEN '2020-07-27' \n" +
            "\tAND '2020-08-02' \n" +
            "GROUP BY\n" +
            "\tcreated_at")
    List<ZbSubOrder> financeStatement(String startTime, String endTime);



}
