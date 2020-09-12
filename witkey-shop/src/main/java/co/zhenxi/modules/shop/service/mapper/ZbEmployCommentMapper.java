/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbEmployComment;
import co.zhenxi.modules.shop.domain.ZbEmployCommentAdvice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-07-23
*/
@Repository
@Mapper
public interface ZbEmployCommentMapper extends CoreMapper<ZbEmployComment> {

    @Select("SELECT\n" +
            "\tzb_employ_comment.* \n" +
            "FROM\n" +
            "\tzb_employ_comment,\n" +
            "\tzb_shop \n" +
            "WHERE\n" +
            "\tzb_shop.uid = zb_employ_comment.to_uid \n" +
            "\tAND zb_shop.id = #{shopId}")
    List<ZbEmployCommentAdvice> getEvaluateByShopId(String shopId);
}
