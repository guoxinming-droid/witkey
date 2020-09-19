/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbVipshopOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
* @author guoke
* @date 2020-07-27
*/
@Repository
@Mapper
public interface ZbVipshopOrderMapper extends CoreMapper<ZbVipshopOrder> {


    @Select("select * from zb_vipshop_order where uid = #{uid} and status = 1")
    ZbVipshopOrder getByUid(Integer uid);
    @Select("select * from zb_vipshop_order where code = #{code} and status = 1")
    ZbVipshopOrder getByCode(String code);

    @Update("update zb_vipshop_order set status = 1 where code = #{tradeNo}")
    void updateByTradeNo(String tradeNo);
}
