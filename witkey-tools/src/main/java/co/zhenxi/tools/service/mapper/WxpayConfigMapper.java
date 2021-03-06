/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.tools.domain.WxpayConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
* @author Guo
* @date 2020-07-20
*/
@Repository
@Mapper
public interface WxpayConfigMapper extends CoreMapper<WxpayConfig> {

    @Select("select * from wxpay_config where uid =${userId}")
    WxpayConfig selectByUserId(long userId);

}
