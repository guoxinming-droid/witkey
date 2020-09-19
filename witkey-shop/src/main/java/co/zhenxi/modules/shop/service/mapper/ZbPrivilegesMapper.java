/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbPackAdvice;
import co.zhenxi.modules.shop.domain.ZbPackage;
import co.zhenxi.modules.shop.domain.ZbPrivileges;
import co.zhenxi.modules.shop.domain.ZbPrivilegesAdvice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Repository
@Mapper
public interface ZbPrivilegesMapper extends CoreMapper<ZbPrivileges> {


    @Update( "update zb_privileges set status = #{status} where id = #{id}")
    void updateOnstatus(@Param("status") int status, @Param("id") int id);

    @Update( "update zb_privileges set is_recommend = #{recommend} where id = #{id}")
    void updateIsRecommend(@Param("recommend") int recommend, @Param("id") int id);


    @Select("select * from zb_privileges where status = 0 order by list asc")
    List<ZbPrivileges> getPrivileges();

    @Select("select id,title,ico,code from zb_privileges where status = 0 order by list desc")
    List<ZbPrivileges> getPrivilegesT();

    @Select("select * from zb_package where status = 0 and id = #{id}")
    @Results( id = "zbpackage",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "logo",property = "logo"),
            @Result(column = "status",property = "status"),
            @Result(column = "price_rules",property = "priceRules"),
            @Result(column = "list",property = "list"),
            @Result(column = "type",property = "type"),
            @Result(column = "typeStatus",property = "typeStatus"),
            @Result(column = "price",property = "price"),
            @Result(column = "createdAt",property = "created_at"),
            @Result(column = "updatedAt",property = "updated_at"),
            @Result(column = "deletedAt",property = "deleted_at"),
            @Result(column = "id",property = "zbPrivilegesList",
                    many = @Many(select = "co.zhenxi.modules.shop.service.mapper.ZbPrivilegesMapper.getPrivilegesAdviceById")),
    }

    )
    ZbPackAdvice getVipInfo(Integer id);




    @Select("select \n" +
            "\tzpp.rule,\n" +
            "zps.*\t\n" +
            "from \n" +
            "\tzb_package zp,\n" +
            "\tzb_package_privileges zpp,\n" +
            "\tzb_privileges zps\n" +
            "\t  where \n" +
            "\t\tzp.status = 0\n" +
            "\t\tAND zp.id = zpp.package_id \n" +
            "\tand zps.id = zpp.privileges_id\n" +
            "\tand zp.id = #{id}")
    List<ZbPrivilegesAdvice> getPrivilegesAdviceById(Integer id);

    @Select("\n" +
            "\t\tselect \n" +
            "  zps.id\n" +
            "from \n" +
            "\tzb_package zp,\n" +
            "\tzb_package_privileges zpp,\n" +
            "\tzb_privileges zps\n" +
            "\t  where \n" +
            "\t\tzp.status = 0\n" +
            "\t\tAND zp.id = zpp.package_id \n" +
            "\tand zps.id = zpp.privileges_id\n" +
            "\tand zp.id = #{packageId}")
    List<Integer> getPrivilegesIds(Integer packageId);




    @Select("select rule from zb_package_privileges where package_id = #{packageId} and privileges_id = #{privilegesId}")
    String getPrivilegesIdRule(Integer packageId,Integer privilegesId);


}
