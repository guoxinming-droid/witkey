/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbShop;
import co.zhenxi.modules.shop.domain.ZbShopAdvice;
import co.zhenxi.modules.shop.domain.ZbTask;
import co.zhenxi.modules.shop.service.handlertype.StringTypeHandler;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Repository
@Mapper
public interface ZbShopMapper extends CoreMapper<ZbShop> {

    @Select("select * From  zb_shop where is_recommend = '1'  ${ew.customSqlSegment} ")
    List<ZbShop> getRecommendShopList(@Param("ew") Wrapper<ZbTask> queryWrapper);

    @Select(" SELECT\n" +
            "\ts.id,\n" +
            "\ts.uid,\n" +
            "\t(SELECT name from zb_users WHERE id = s.uid) AS userName,\n" +
            "\ts.type,\n" +
            "\ts.shop_pic,\n" +
            "\ts.shop_name,\n" +
            "\ts.shop_desc,\n" +
            "\ts.province,\n" +
            "\ts.city,\n" +
            "\t(SELECT name from zb_district where s.city = id) AS cityName,\n" +
            "\ts.STATUS,\n" +
            "\ts.created_at,\n" +
            "\ts.updated_at,\n" +
            "\ts.shop_bg,\n" +
            "\ts.seo_title,\n" +
            "\ts.seo_keyword,\n" +
            "\ts.seo_desc,\n" +
            "\ts.is_recommend\n" +
            "FROM\n" +
            "\tzb_shop s  where s.id = ${id} ")
    ZbShopAdvice getShopByid(Integer id);

    @Select("SELECT DISTINCT\n" +
            "\tzb_shop.*,\n" +
            "\t(\n" +
            "SELECT\n" +
            "\tcount( * ) \n" +
            "FROM\n" +
            "\tzb_shop,\n" +
            "\tzb_shop_order \n" +
            "WHERE\n" +
            "\tzb_shop.uid = zb_shop_order.uid \n" +
            "\tAND zb_shop_order.STATUS = 4 \n" +
            "\tAND zb_shop.uid = #{uid}) num \n" +
            "FROM\n" +
            "\tzb_shop,\n" +
            "\tzb_shop_focus,\n" +
            "\tzb_tag_shop,\n" +
            "\tzb_skill_tags\n" +
            "\t\n" +
            "WHERE\n" +
            "\tzb_shop.id = zb_shop_focus.shop_id \n" +
            "\tAND zb_shop_focus.uid = #{uid}")
    @Results(
            @Result(property = "num",column = "num" ,typeHandler = StringTypeHandler.class)
    )
    List<ZbShopAdvice> getCollectShop(Integer uid);

    @Select("SELECT DISTINCT\n" +
            "\tzb_skill_tags.tag_name \n" +
            "FROM\n" +
            "\tzb_shop,\n" +
            "\tzb_tag_shop,\n" +
            "\tzb_shop_focus,\n" +
            "\tzb_skill_tags \n" +
            "WHERE\n" +
            "\tzb_shop.id = zb_tag_shop.shop_id \n" +
            "\tAND zb_skill_tags.id = zb_tag_shop.tag_id \n" +
            "\tAND zb_shop.id = zb_shop_focus.shop_id \n" +
            "\tAND zb_shop_focus.uid = #{uid} \n" +
            "\tAND zb_shop.id = #{shopId}")
    List<String> getCollectShop1(Integer uid,Integer shopId);

    @Select("SELECT\n" +
            "\tzbs.id,\n" +
            "\tzbs.shop_pic \n" +
            "FROM\n" +
            "\tzb_shop zbs,\n" +
            "\tzb_vipshop_order zbvo \n" +
            "WHERE\n" +
            "\tzbs.id = zbvo.shop_id \n" +
            "\tAND zbvo.STATUS = 1 \n" +
            "ORDER BY\n" +
            "\tzbvo.package_id DESC")
    Page<Map<String, Object>> getShopByVip();

    @Select("select zs.*,zd.name sName from zb_shop zs,zb_district zd where zs.is_recommend = 1   and zs.city= zd.id order by zs.good_comment desc")
    @Results(id = "zbshop" ,value = {
            @Result(column = "province" ,property = "pName",
                    one = @One(select = ("co.zhenxi.modules.shop.service.mapper.ZbDistrictMapper.getAllById2")
                    )
            )
    })
    Page<ZbShopAdvice> getRecommendShop();


    @Select("select *,(select count(1) from zb_employ,zb_shop where zb_employ.employee_uid = zb_shop.uid) as serviceCount from zb_shop where id =#{id}")
    ZbShop getShopByid1(Integer shopId);

    @Select("SELECT\n" +
            "\tzb_district.NAME AS cityNmae \n" +
            "FROM\n" +
            "\tzb_shop,\n" +
            "\tzb_goods,\n" +
            "\tzb_district \n" +
            "WHERE\n" +
            "\tzb_goods.shop_id = zb_shop.id \n" +
            "\tAND zb_district.id = zb_shop.city \n" +
            "\tAND zb_goods.id = #{id}")
    Map<String,Object> selectByGoodsId(Integer id);

}
