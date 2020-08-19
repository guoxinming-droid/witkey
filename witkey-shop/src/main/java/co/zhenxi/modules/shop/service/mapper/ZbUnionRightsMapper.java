/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbUnionRights;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-07-30
*/
@Repository
@Mapper
public interface ZbUnionRightsMapper extends CoreMapper<ZbUnionRights> {

    @Select("SELECT\n" +
            "  id,\n" +
            "\ttype,\n" +
            "\tobject_id,\n" +
            "\tobject_type,\n" +
            "\tdes,\n" +
            "\tSTATUS,\n" +
            "\tfrom_uid,\n" +
            "\t(select name from zb_users where id = from_uid) AS fromName,\n" +
            "\tto_uid,\n" +
            "\thandel_uid,\n" +
            "\tcreated_at,\n" +
            "\thandled_at,\n" +
            "\tis_delete \n" +
            "FROM\n" +
            "\tzb_union_rights")
    List<ZbUnionRights> ShopRightsList(@Param("ew") Wrapper<ZbUnionRights> queryWrapper);

}
