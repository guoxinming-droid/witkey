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
import co.zhenxi.modules.shop.domain.ZbArticleCategory;
import co.zhenxi.modules.shop.service.ZbArticleCategoryService;
import co.zhenxi.modules.shop.service.dto.ZbArticleCategoryDto;
import co.zhenxi.modules.shop.service.dto.ZbArticleCategoryQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbArticleCategoryMapper;
import co.zhenxi.utils.FileUtil;
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
//@CacheConfig(cacheNames = "zbArticleCategory")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbArticleCategoryServiceImpl extends BaseServiceImpl<ZbArticleCategoryMapper, ZbArticleCategory> implements ZbArticleCategoryService {
    List<Integer> childArticle = new ArrayList<>();
    private final IGenerator generator;
    private final ZbArticleCategoryMapper zbArticleCategoryMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbArticleCategoryQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbArticleCategory> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbArticleCategoryDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> categoryFooterList(ZbArticleCategoryQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        criteria.setCatId(1);
        PageInfo<ZbArticleCategory> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbArticleCategoryDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> categoryList(ZbArticleCategoryQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        criteria.setCatId(3);
        PageInfo<ZbArticleCategory> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbArticleCategoryDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbArticleCategory> queryAll(ZbArticleCategoryQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbArticleCategory.class, criteria));
    }


    @Override
    public void download(List<ZbArticleCategoryDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbArticleCategoryDto zbArticleCategory : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("文章分类父编号", zbArticleCategory.getPid());
            map.put("分类名称", zbArticleCategory.getCateName());
            map.put("文章数量", zbArticleCategory.getArticles());
            map.put("排序", zbArticleCategory.getDisplayOrder());
            map.put("链接地址", zbArticleCategory.getUrl());
            map.put("用户编号", zbArticleCategory.getUserId());
            map.put("用户名", zbArticleCategory.getUserName());
            map.put("SEO描述", zbArticleCategory.getDescription());
            map.put("SEO标题", zbArticleCategory.getSeotitle());
            map.put("SEO关键词", zbArticleCategory.getKeyword());
            map.put("创建时间", zbArticleCategory.getCreatedAt());
            map.put("修改时间", zbArticleCategory.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<Integer> digui(List<Integer> zbArticleCategory) {
        childArticle.clear();
        List<Integer> retList1 = new ArrayList<Integer>();
        for (Integer pid : zbArticleCategory) {
            retList1 = zbArticleCategoryMapper.selectByPid(pid);
            if (retList1.size() > 0) {
                childArticle.addAll(retList1);
                digui(retList1); //循环调用自己
            }
            childArticle.addAll(zbArticleCategory);
        }
        return childArticle;
    }




    @Override
    public List<Integer> selectByPid(Integer id) {
        return zbArticleCategoryMapper.selectByPid(id);
    }

}
