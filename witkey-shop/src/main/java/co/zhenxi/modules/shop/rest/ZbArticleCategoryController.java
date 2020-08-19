/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbArticleCategory;
import co.zhenxi.modules.shop.service.ZbArticleCategoryService;
import co.zhenxi.modules.shop.service.dto.ZbArticleCategoryDto;
import co.zhenxi.modules.shop.service.dto.ZbArticleCategoryQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
* @author guoke
* @date 2020-07-30
*/
@AllArgsConstructor
@Api(tags = "文章分类管理")
@RestController
@RequestMapping("/api/zbArticleCategory")
public class ZbArticleCategoryController {

    private final ZbArticleCategoryService zbArticleCategoryService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbArticleCategory:list')")
    public void download(HttpServletResponse response, ZbArticleCategoryQueryCriteria criteria) throws IOException {
        zbArticleCategoryService.download(generator.convert(zbArticleCategoryService.queryAll(criteria), ZbArticleCategoryDto.class), response);
    }

    @GetMapping
    @Log("查询文章分类")
    @ApiOperation("查询文章分类")
    @PreAuthorize("@el.check('admin','zbArticleCategory:list')")
    public ResponseEntity<Object> getZbArticleCategorys(ZbArticleCategoryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbArticleCategoryService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增文章分类")
    @ApiOperation("新增文章分类")
    @PreAuthorize("@el.check('admin','zbArticleCategory:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbArticleCategory resources){
        return new ResponseEntity<>(zbArticleCategoryService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改文章分类")
    @ApiOperation("修改文章分类")
    @PreAuthorize("@el.check('admin','zbArticleCategory:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbArticleCategory resources){
        zbArticleCategoryService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除文章分类")
    @ApiOperation("删除文章分类")
    @PreAuthorize("@el.check('admin','zbArticleCategory:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbArticleCategoryService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/categoryFooterList")
    @Log("查询页脚分类")
    @ApiOperation("查询页脚分类")
    //@PreAuthorize("@el.check('admin','zbArticleCategory:list')")
    @AnonymousAccess
    public ResponseEntity<Object> categoryFooterList(ZbArticleCategoryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbArticleCategoryService.categoryFooterList(criteria,pageable),HttpStatus.OK);
    }

    @GetMapping("/categoryList")
    @Log("查询文章分类")
    @ApiOperation("查询文章分类")
    //@PreAuthorize("@el.check('admin','zbArticleCategory:list')")
    @AnonymousAccess
    public ResponseEntity<Object> categoryList(ZbArticleCategoryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbArticleCategoryService.categoryList(criteria,pageable),HttpStatus.OK);
    }
}
