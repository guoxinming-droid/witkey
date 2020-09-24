/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.modules.shop.domain.ZbArticle;
import co.zhenxi.modules.shop.service.ZbArticleCategoryService;
import co.zhenxi.modules.shop.service.ZbArticleService;
import co.zhenxi.modules.shop.service.dto.ZbArticleDto;
import co.zhenxi.modules.shop.service.dto.ZbArticleQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbArticleMapper;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author guoke
* @date 2020-07-30
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbArticle")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbArticleServiceImpl extends BaseServiceImpl<ZbArticleMapper, ZbArticle> implements ZbArticleService {

    private final IGenerator generator;
    private final ZbArticleCategoryService  zbArticleCategoryService;
    private final ZbArticleMapper zbArticleMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbArticleQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbArticle> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbArticleDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> articleFooter(ZbArticleQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        List<Integer> zbArticleCategoryDto = zbArticleCategoryService.selectByPid(3);
        if(!zbArticleCategoryDto.isEmpty()){
            criteria.setCatId(zbArticleCategoryService.digui(zbArticleCategoryDto));
        }
        PageInfo<ZbArticle> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbArticleDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> article(ZbArticleQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        List<Integer> zbArticleCategoryDto = zbArticleCategoryService.selectByPid(1);
        if(!zbArticleCategoryDto.isEmpty()){
            criteria.setCatId(zbArticleCategoryService.digui(zbArticleCategoryDto));
        }
        PageInfo<ZbArticle> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbArticleDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbArticle> queryAll(ZbArticleQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbArticle.class, criteria));
    }


    @Override
    public void download(List<ZbArticleDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbArticleDto zbArticle : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("分类编号", zbArticle.getCatId());
            map.put("用户编号", zbArticle.getUserId());
            map.put("用户名", zbArticle.getUserName());
            map.put("标题", zbArticle.getTitle());
            map.put("作者", zbArticle.getAuthor());
            map.put("来源", zbArticle.getFroms());
            map.put("来源地址", zbArticle.getFromurl());
            map.put("文章地址", zbArticle.getUrl());
            map.put("简介", zbArticle.getSummary());
            map.put("新闻列表图片", zbArticle.getPic());
            map.put(" thumb",  zbArticle.getThumb());
            map.put(" tag",  zbArticle.getTag());
            map.put(" status",  zbArticle.getStatus());
            map.put("文字内容", zbArticle.getContent());
            map.put("文章阅读浏览次数", zbArticle.getViewTimes());
            map.put("SEO标题", zbArticle.getSeotitle());
            map.put("SEO关键词", zbArticle.getKeywords());
            map.put("SEO描述", zbArticle.getDescription());
            map.put("排序", zbArticle.getDisplayOrder());
            map.put("是否推荐 1->是 2->否", zbArticle.getIsRecommended());
            map.put("添加时间", zbArticle.getCreatedAt());
            map.put("修改时间", zbArticle.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 获取资讯文章
     *
     * @param size
     */
    @Override
    public Map<String, Object> getArticle(Pageable size) {
        getPage(size);
        Page<ZbArticle> page = zbArticleMapper.getArticle();
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getResult(), ZbArticle.class));
        map.put("pageNum",page.getPageNum());
        map.put("pages",page.getPages());
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> getArticleTitle(Integer articleId) {
        return zbArticleMapper.getArticleTitle(articleId);
    }

    @Override
    public Map<String, Object> getRelevantArticleTitle(Integer articleId) {
        return zbArticleMapper.getArticleTitleByCatId(articleId);
    }
}
