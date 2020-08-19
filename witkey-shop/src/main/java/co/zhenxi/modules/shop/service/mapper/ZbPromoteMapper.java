/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbPromote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-08-06
*/
@Repository
@Mapper
public interface ZbPromoteMapper extends CoreMapper<ZbPromote> {

    @Select("SELECT p.id,p.from_uid,u.name AS fromName,p.to_uid,u1.name AS toName,p.price,p.finish_conditions,p.type,p.status,p.created_at,p.updated_at FROM  zb_promote AS p LEFT JOIN zb_users AS u ON p.to_uid = u.id LEFT JOIN zb_users AS u1 ON p.from_uid = u1.id ${whereSql} ORDER BY p.created_at DESC ")
    List<ZbPromote> promoteRelation(String whereSql);

}
