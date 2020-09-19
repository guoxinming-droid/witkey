/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbPackage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Repository
@Mapper
public interface ZbPackageMapper extends CoreMapper<ZbPackage> {

    @Update( "update zb_package set status = #{status} where id = #{id}")
    void updateOnstatus(@Param("status") int status, @Param("id") int id);

    @Select("select * from zb_package where status = 0 order by price")
    List<ZbPackage> getPackage();

    @Select("select id,title,price from zb_package where status = 0 order by price asc")
    List<ZbPackage> getVipInfoT();

}
