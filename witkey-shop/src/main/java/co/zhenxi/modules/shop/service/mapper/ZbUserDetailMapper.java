/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbUserDetail;
import co.zhenxi.modules.shop.domain.ZbUserDetailAdvice;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;

/**
* @author guoke
* @date 2020-07-27
*/
@Repository
@Mapper
public interface ZbUserDetailMapper extends CoreMapper<ZbUserDetail> {

    @Update("UPDATE zb_user_detail set balance = balance + #{balances}   where uid = #{uid}")
    boolean updateUserDetail(Integer uid , BigDecimal balances);


        @Select("SELECT\n" +
                "DISTINCT\n" +
                "\tzb_user_detail.*, " +
                "zb_cate.name as cateName,\n" +
                "zb_users.name as userName,\n" +
                "zb_shop.id as shopId\n" +
                "FROM\n" +
                "\tzb_user_detail,\n" +
                "\tzb_users,\n" +
                "\tzb_shop, \n" +
                "\tzb_cate \n" +
                "WHERE\n" +
                "\tzb_users.STATUS = 1 \n" +
                "\tAND zb_users.id = zb_user_detail.uid \n" +
                "\tAND zb_shop.uid = zb_users.id ${whereSql}")
        Page<ZbUserDetailAdvice> selectAll(String whereSql);


        @Select("SELECT\n" +
                "\tzb_users.NAME AS username,\n" +
                "\tifnull(zb_user_detail.avatar,'') as avatar ,\n" +
                "\tifnull(zb_district.NAME,'') as cityname,\n" +
                "\tifnull(zb_user_detail.sign,'') as sign,\n" +
                "\tifnull(zb_user_detail.employee_praise_rate,0) as goodCount,\n" +
                "\tifnull(CONCAT( TRUNCATE ( ( ( zb_user_detail.employee_praise_rate / zb_user_detail.receive_task_num ) ) * 100, 2 ), '%' ),'100%') AS goodComment,\n" +
                "\tifnull((\n" +
                "SELECT\n" +
                "\tcount( 1 ) \n" +
                "FROM\n" +
                "\tzb_employ \n" +
                "WHERE\n" +
                "\tPERIOD_DIFF( date_format( now( ), '%Y%m' ), date_format( created_at, '%Y%m' ) ) = 1 \n" +
                "\tAND employee_uid = zb_users.id \n" +
                "\tAND STATUS = 4 \n" +
                "\t) ,0)AS count \n" +
                "FROM\n" +
                "\tzb_user_detail,\n" +
                "\tzb_users,\n" +
                "\tzb_district \n" +
                "WHERE\n" +
                "\tzb_user_detail.uid = zb_users.id \n" +
                "\tAND zb_district.id = zb_user_detail.province \n" +
                "\tAND zb_users.id = #{uid}")
    Map<String, Object> getServiceProviderById(Integer uid);
}
