/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbInterview;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-07-27
*/
@Repository
@Mapper
public interface ZbInterviewMapper extends CoreMapper<ZbInterview> {

    @Select("SELECT\n" +
            "\tzb_interview.* \n" +
            "FROM\n" +
            "\tzb_interview \n" +
            "WHERE\n" +
            "\tshop_id IN (\n" +
            "SELECT\n" +
            "\tzb_shop.id \n" +
            "FROM\n" +
            "\tzb_shop,\n" +
            "\tzb_shop_package \n" +
            "WHERE\n" +
            "\tzb_shop.id = zb_shop_package.shop_id \n" +
            "\tAND zb_shop_package.package_id != 0 \n" +
            "\tAND zb_shop.STATUS = 1 \n" +
            "\tAND zb_shop_package.end_time > NOW( ) \n" +
            "\t) order by list")
    List<ZbInterview> getInterview();

    @Select("SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tzb_interview \n" +
            "WHERE\n" +
            "\tshop_id IN ( SELECT shop_id FROM zb_shop_package WHERE STATUS = 0 )")
    Page<ZbInterview> getInterviewByVip();

}
