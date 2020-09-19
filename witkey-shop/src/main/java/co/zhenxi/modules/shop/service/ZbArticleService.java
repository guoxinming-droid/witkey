/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbArticle;
import co.zhenxi.modules.shop.service.dto.ZbArticleDto;
import co.zhenxi.modules.shop.service.dto.ZbArticleQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-07-30
*/
public interface ZbArticleService  extends BaseService<ZbArticle>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbArticleQueryCriteria criteria, Pageable pageable);


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> articleFooter(ZbArticleQueryCriteria criteria, Pageable pageable);

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> article(ZbArticleQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbArticleDto>
    */
    List<ZbArticle> queryAll(ZbArticleQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbArticleDto> all, HttpServletResponse response) throws IOException;

    /**
     * 获取资讯文章
     */
    Map<String, Object> getArticle(Pageable size);

    /**
     * 获取资讯标题
     * @param articleId
     * @return
     */
    Map<String,Object> getArticleTitle(Integer articleId);

    Map<String,Object> getRelevantArticleTitle(Integer articleId);
}
