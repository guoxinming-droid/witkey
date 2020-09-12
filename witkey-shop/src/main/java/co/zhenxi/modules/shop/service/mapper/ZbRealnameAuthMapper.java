/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbRealnameAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
* @author guoke
* @date 2020-08-05
*/
@Repository
@Mapper
public interface ZbRealnameAuthMapper extends CoreMapper<ZbRealnameAuth> {

    @Update( "update zb_realname_auth set status = #{status},auth_time = now() where id = #{id} ")
    void updateOnstatus(@Param("status") int status, @Param("id") int id);

    @Select("select * from zb_realname_auth where uid = #{uid} and type = #{i}")
    ZbRealnameAuth getByUid(Integer uid, int i);

}
