/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbArticle;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* @author guoke
* @date 2020-07-30
*/
@Repository
@Mapper
public interface ZbArticleMapper extends CoreMapper<ZbArticle> {
    @Select("select *from zb_article where is_recommended = 1 order by description")
    Page<ZbArticle> getArticle();

    @Select("select id , title from zb_article where id = #{articleId}")
    Map<String, Object> getArticleTitle(Integer articleId);

    @Select("SELECT\n" +
            "\tid,\n" +
            "\ttitle,\n" +
            "\tcreated_at \n" +
            "FROM\n" +
            "\tzb_article \n" +
            "WHERE\n" +
            "\tcat_id = ( SELECT cat_id FROM zb_article WHERE id = #{articleId} ) \n" +
            "ORDER BY\n" +
            "\tcreated_at DESC \n" +
            "\tLIMIT 5")
    Map<String, Object> getArticleTitleByCatId(Integer articleId);

}
