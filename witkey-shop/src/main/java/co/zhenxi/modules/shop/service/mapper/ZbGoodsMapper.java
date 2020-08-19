/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbGoods;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<ZbGoods> selectGoodsByShopId(String whereSql);

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

}
