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
import co.zhenxi.modules.shop.domain.ZbArticle;
import co.zhenxi.modules.shop.service.ZbArticleService;
import co.zhenxi.modules.shop.service.dto.ZbArticleDto;
import co.zhenxi.modules.shop.service.dto.ZbArticleQueryCriteria;
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
@Api(tags = "文章页脚管理管理")
@RestController
@RequestMapping("/api/zbArticle")
public class ZbArticleController {

    private final ZbArticleService zbArticleService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbArticle:list')")
    public void download(HttpServletResponse response, ZbArticleQueryCriteria criteria) throws IOException {
        zbArticleService.download(generator.convert(zbArticleService.queryAll(criteria), ZbArticleDto.class), response);
    }

    @GetMapping
    @Log("查询文章页脚管理")
    @ApiOperation("查询文章页脚管理")
    @PreAuthorize("@el.check('admin','zbArticle:list')")
    public ResponseEntity<Object> getZbArticles(ZbArticleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbArticleService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增文章页脚管理")
    @ApiOperation("新增文章页脚管理")
    @PreAuthorize("@el.check('admin','zbArticle:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbArticle resources){
        return new ResponseEntity<>(zbArticleService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改文章页脚管理")
    @ApiOperation("修改文章页脚管理")
    @PreAuthorize("@el.check('admin','zbArticle:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbArticle resources){
        zbArticleService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除文章页脚管理")
    @ApiOperation("删除文章页脚管理")
    @PreAuthorize("@el.check('admin','zbArticle:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbArticleService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/articleFooter")
    @Log("查询页脚管理列表")
    @ApiOperation("查询页脚管理列表")
    //@PreAuthorize("@el.check('admin','zbArticle:list')")
    @AnonymousAccess
    public ResponseEntity<Object> articleFooter(ZbArticleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbArticleService.articleFooter(criteria,pageable),HttpStatus.OK);
    }

    @GetMapping("/article")
    @Log("查询文章管理列表")
    @ApiOperation("查询文章管理列表")
    //@PreAuthorize("@el.check('admin','zbArticle:list')")
    @AnonymousAccess
    public ResponseEntity<Object> article(ZbArticleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbArticleService.article(criteria,pageable),HttpStatus.OK);
    }
}
