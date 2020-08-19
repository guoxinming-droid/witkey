/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbShopFocus;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author guoke
* @date 2020-08-19
*/
@Repository
@Mapper
public interface ZbShopFocusMapper extends CoreMapper<ZbShopFocus> {

    @Delete(" DELETE FROM zb_shop_focus WHERE uid = ${uid} AND shop_id = ${shopId} ")
    Boolean removeByUid(Integer uid, Integer shopId);

}
