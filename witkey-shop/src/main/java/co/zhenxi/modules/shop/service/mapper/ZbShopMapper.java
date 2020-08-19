/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbShop;
import co.zhenxi.modules.shop.domain.ZbTask;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    ZbShop getShopByid(Integer id);

}
