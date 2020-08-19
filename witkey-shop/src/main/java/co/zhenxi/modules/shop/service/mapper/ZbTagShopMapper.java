/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbTagShop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-08-15
*/
@Repository
@Mapper
public interface ZbTagShopMapper extends CoreMapper<ZbTagShop> {

    @Select("select id,tag_id,shop_id from zb_tag_shop where shop_id = ${id}")
    List<ZbTagShop> getTagShopByShopId(Integer id);

}
