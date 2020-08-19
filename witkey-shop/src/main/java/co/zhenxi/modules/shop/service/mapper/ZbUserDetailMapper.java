/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbUserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
* @author guoke
* @date 2020-07-27
*/
@Repository
@Mapper
public interface ZbUserDetailMapper extends CoreMapper<ZbUserDetail> {

    @Update("UPDATE zb_user_detail set balance = balance + #{balances}   where uid = #{uid}")
    boolean updateUserDetail(Integer uid , BigDecimal balances);

}
