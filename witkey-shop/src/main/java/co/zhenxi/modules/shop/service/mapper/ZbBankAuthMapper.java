/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbBankAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
* @author Guo xinming
* @date 2020-07-16
*/
@Repository
@Mapper
public interface ZbBankAuthMapper extends CoreMapper<ZbBankAuth> {

    @Update( "update zb_bank_auth set status = #{status},auth_time = now() where id = #{id} ")
    void updateOnstatus(@Param("status") int status, @Param("id") int id);

    @Select("select * from zb_bank_auth where uid = #{uid}")
    ZbBankAuth getByUid(Integer uid);
}
