/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbGoodsComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-07-23
*/
@Repository
@Mapper
public interface ZbGoodsCommentMapper extends CoreMapper<ZbGoodsComment> {

    @Select(" SELECT\n" +
            "\tgc.id,\n" +
            "\tgc.goods_id,\n" +
            "\tgc.uid,\n" +
            "\tgc.comment_by,\n" +
            "\tgc.speed_score,\n" +
            "\tgc.quality_score,\n" +
            "\tgc.attitude_score,\n" +
            "\tgc.comment_desc,\n" +
            "\tgc.type,\n" +
            "\tgc.created_at,\n" +
            "\tu.name,\n" +
            "  ud.avatar\t\n" +
            "FROM\n" +
            "\tzb_goods_comment AS gc\n" +
            "\tLEFT JOIN zb_users AS u ON gc.uid = u.id\n" +
            "\tLEFT JOIN zb_user_detail AS ud ON gc.uid = ud.id  ${whereSql}")
    List<ZbGoodsComment> getGoodsCommentByGoodId(String whereSql);
    @Select("SELECT\n" +
            "\tconcat\n" +
            "\t((select count(1)  from zb_goods_comment where zb_goods_comment.goods_id =#{goodsId} and type = 0)/count( 1 )*100,\"%\") AS '好评率',\n" +
            "\tavg( speed_score ) AS '速度得分',\n" +
            "\tavg(quality_score) as '质量得分',\n" +
            "\tavg(attitude_score) as '态度得分'\n" +
            "FROM\n" +
            "\tzb_goods_comment \n" +
            "WHERE\n" +
            "\tzb_goods_comment.goods_id = #{goodsId}")
    Map<String, Object> getAnyCommentByGoodsId(Integer goodsId);

    @Select("select count(1) as '好评数量' from zb_goods_comment where zb_goods_comment.goods_id =#{goodsId} and type = 0")
    Map<String, Object> getGoodCommentByGoodsId(Integer goodsId);
}
