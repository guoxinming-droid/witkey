/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbGoods;
import co.zhenxi.modules.shop.domain.ZbGoodsAdvice;
import co.zhenxi.modules.shop.domain.ZbGoodsComment;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.pagehelper.Page;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-07-29
*/
@Repository
@Mapper
public interface ZbGoodsMapper extends CoreMapper<ZbGoods> {

    @Select("SELECT\n" +
            "\tid,\n" +
            "\tuid,\n" +
            "\tshop_id,\n" +
            "\tcate_id,\n" +
            "\ttitle,\n" +
            "\tunit,\n" +
            "\ttype,\n" +
            "CASE\n" +
            "\t\ttype \n" +
            "\t\tWHEN '1' THEN\n" +
            "\t\t'作品' \n" +
            "\t\tWHEN '2' THEN\n" +
            "\t\t'服务'  \n" +
            "\tEND AS types,\n" +
            "\tcash,\n" +
            "\tcover,\n" +
            "\tSTATUS,\n" +
            "\ttool_expiration_time,\n" +
            "\tis_recommend,\n" +
            "\tsales_num,\n" +
            "\trecommend_end,\n" +
            "\tcomments_num,\n" +
            "\tgood_comment,\n" +
            "\tview_num,\n" +
            "is_delete,\n" +
            "recommend_text,\n" +
            "seo_title,\n" +
            "seo_keyword,\n" +
            "seo_desc,\n" +
            "DATE_FORMAT(created_at,'%Y-%m-%d %H:%i:%S') AS created_at,\n" +
            "DATE_FORMAT(updated_at,'%Y-%m-%d %H:%i:%S') AS updated_at\n" +
            "FROM\n" +
            "\tzb_goods  ${ew.customSqlSegment}")
    List<ZbGoods> getRecommendGoodsList(@Param("ew") Wrapper<ZbGoods> queryWrapper);

    @Select(" SELECT\n" +
            "\tid,\n" +
            "\tuid,\n" +
            "\t(SELECT name FROM zb_users where id =uid ) AS userName,\n" +
            "\tshop_id,\n" +
            "\tcate_id,\n" +
            "\ttitle,\n" +
            "\tdes,\n" +
            "\tunit,\n" +
            "\ttype,\n" +
            "\tcash,\n" +
            "\tcover,\n" +
            "\tSTATUS,\n" +
            "\tis_recommend,\n" +
            "\trecommend_end,\n" +
            "\tsales_num,\n" +
            "\tcomments_num,\n" +
            "\tview_num,\n" +
            "\tis_delete,\n" +
            "\trecommend_text,\n" +
            "\tseo_title,\n" +
            "\tseo_keyword,\n" +
            "\tseo_desc,\n" +
            "\tgood_comment \n" +
            "FROM\n" +
            "\tzb_goods ${whereSql} ")
    Page<ZbGoods> selectGoodsByShopId(String whereSql);

    @Select("SELECT\n" +
            "CASE\n" +
            "\ttype \n" +
            "\tWHEN 1 THEN\n" +
            "\t'作品' ELSE '服务' \n" +
            "\tEND AS '类型',\n" +
            "\t( count( 1 ) ) AS count \n" +
            "FROM\n" +
            "\tzb_goods zg \n" +
            "WHERE\n" +
            "\tzg.type = #{type} \n" +
            "\tAND zg.shop_id = #{shopId}")
    Map<String ,Object> selectGoodsCountByShopId(Integer type,Integer shopId);

    @Select(" SELECT\n" +
            "\tid,\n" +
            "\tuid,\n" +
            "\t(SELECT name FROM zb_users where id =uid ) AS userName,\n" +
            "\tshop_id,\n" +
            "\tcate_id,\n" +
            "\ttitle,\n" +
            "\tdes,\n" +
            "\tunit,\n" +
            "\ttype,\n" +
            "\tcash,\n" +
            "\tcover,\n" +
            "\tSTATUS,\n" +
            "\tis_recommend,\n" +
            "\trecommend_end,\n" +
            "\tsales_num,\n" +
            "\tcomments_num,\n" +
            "\tview_num,\n" +
            "\tis_delete,\n" +
            "\trecommend_text,\n" +
            "\tseo_title,\n" +
            "\tseo_keyword,\n" +
            "\tseo_desc,\n" +
            "\tgood_comment \n" +
            "FROM\n" +
            "\tzb_goods where status = '1'  AND is_delete = '0' AND id = ${id} ")
    List<ZbGoods> selectById(Integer id);


    @Select("SELECT\n" +
            "\tzbg.*,\n" +
            "\tzbu.NAME uName \n" +
            "FROM\n" +
            "\tzb_goods zbg,\n" +
            "\tzb_users zbu \n" +
            "WHERE\n" +
            "\tzbg.uid = zbu.id \n" +
            "\tAND zbg.STATUS = 1 \n" +
            "\tAND zbg.is_recommend = 1 \n" +
            "\tAND recommend_end > NOW( )\n" +
            "\t ${sql}")
    Page<ZbGoods> getGoods(String sql);

    @Select("select \n" +
            "( SELECT NAME FROM zb_cate WHERE id = cate_id ) catename,\n" +
            "\t( count( 1 ) ) count,\n" +
            "\tssm.cate_id\n" +
            "\tfrom\n" +
            "(SELECT\n" +
            "\t *\n" +
            "FROM\n" +
            "\tzb_goods \n" +
            "WHERE\n" +
            "\t1 = 1 \n" +
            "\tAND shop_id = #{shopId} \n" +
            "\tAND type = #{type} \n" +
            "\tAND STATUS = 1 \n" +
            "\tAND is_delete = 0 ) ssm\n" +
            "GROUP BY\n" +
            "\tssm.cate_id  ")
    List<Map<String ,Object>> getCateNameAndCount(String shopId, String type);



    @Select("select * from zb_goods zg , zb_goods_comment zgc where zg.id = zgc.goods_id and zg.shop_id = #{shopId}")
    List<ZbGoodsComment> selectGoodsCommentByShopId(Integer shopId);


    @Select("SELECT\n" +
            "\ttype,\n" +
            "CASE\n" +
            "\ttype \n" +
            "\tWHEN 1 THEN\n" +
            "\t\"作品\" \n" +
            "\tWHEN 2 THEN\n" +
            "\t\"服务\" ELSE \"其他\" end as 类型\n" +
            "FROM\n" +
            "\tzb_goods \n" +
            "GROUP BY\n" +
            "\ttype ")
    List<Map<String, Object>> getGoodsType();

    @Select("SELECT\n" +
            "\t( SELECT NAME FROM zb_cate, zb_goods WHERE zb_cate.id = zb_goods.cate_pid and zb_goods.id=#{goodsId} ) AS tagPame,\n" +
            "\t( SELECT NAME FROM zb_cate, zb_goods WHERE zb_cate.id = zb_goods.cate_id and zb_goods.id=#{goodsId}) AS tagName,\n" +
            "\tzb_goods.* \n" +
            "FROM\n" +
            "\tzb_goods \n" +
            "WHERE\n" +
            "\tid = #{goodsId}")
    ZbGoodsAdvice getGoodsByType(Integer goodsId);

    @Select("SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tzb_goods \n" +
            "WHERE\n" +
            "\tzb_goods.shop_id = ( SELECT zb_goods.shop_id FROM zb_goods, zb_shop WHERE zb_goods.id =#{goodsId} AND zb_shop.id = zb_goods.shop_id ) \n" +
            "AND zb_goods.id != #{goodsId}\n" +
            "\tand status = 1\n" +
            "\tand is_recommend = 0\n" +
            "\tand type = 1\n" +
            "\torder by view_num desc")
    Page<ZbGoods> getGoodsByOther(Integer goodsId);

    @Select("" +
            "select \n" +
            "id ," +
            "title ," +
            "( SELECT NAME FROM zb_cate, zb_goods WHERE zb_cate.id = zb_goods.cate_pid and zb_goods.id=#{goodsId} ) AS tagPame," +
            "( SELECT NAME FROM zb_cate, zb_goods WHERE zb_cate.id = zb_goods.cate_id and zb_goods.id=#{goodsId}) AS tagName," +
            "cash\n" +
            "from\n" +
            "zb_goods \n" +
            "where \n" +
            "status = 1 \n" +
            "AND \n" +
            "is_delete = 0 \n" +
            "AND id = #{goodsId}")
    Map<String ,Object> selectGoodsById(Integer goodsId);
}
