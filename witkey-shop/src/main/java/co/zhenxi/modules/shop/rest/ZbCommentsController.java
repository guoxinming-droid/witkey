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
import co.zhenxi.modules.shop.domain.ZbComments;
import co.zhenxi.modules.shop.service.ZbCommentsService;
import co.zhenxi.modules.shop.service.dto.ZbCommentsDto;
import co.zhenxi.modules.shop.service.dto.ZbCommentsQueryCriteria;
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
* @date 2020-07-23
*/
@AllArgsConstructor
@Api(tags = "评分表管理")
@RestController
@RequestMapping("/api/zbComments")
public class ZbCommentsController {

    private final ZbCommentsService zbCommentsService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbComments:list')")
    public void download(HttpServletResponse response, ZbCommentsQueryCriteria criteria) throws IOException {
        zbCommentsService.download(generator.convert(zbCommentsService.queryAll(criteria), ZbCommentsDto.class), response);
    }

    @GetMapping("/list")
    @Log("查询评分表")
    @ApiOperation("查询评分表")
    //@PreAuthorize("@el.check('admin','zbComments:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getZbCommentss(ZbCommentsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbCommentsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增评分表")
    @ApiOperation("新增评分表")
    @PreAuthorize("@el.check('admin','zbComments:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbComments resources){
        return new ResponseEntity<>(zbCommentsService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改评分表")
    @ApiOperation("修改评分表")
    @PreAuthorize("@el.check('admin','zbComments:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbComments resources){
        zbCommentsService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除评分表")
    @ApiOperation("删除评分表")
    @PreAuthorize("@el.check('admin','zbComments:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbCommentsService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
