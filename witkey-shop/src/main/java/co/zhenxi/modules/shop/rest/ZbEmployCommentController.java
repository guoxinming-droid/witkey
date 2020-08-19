/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbEmployComment;
import co.zhenxi.modules.shop.service.ZbEmployCommentService;
import co.zhenxi.modules.shop.service.dto.ZbEmployCommentDto;
import co.zhenxi.modules.shop.service.dto.ZbEmployCommentQueryCriteria;
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
@Api(tags = "评价管理")
@RestController
@RequestMapping("/api/zbEmployComment")
public class ZbEmployCommentController {

    private final ZbEmployCommentService zbEmployCommentService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbEmployComment:list')")
    public void download(HttpServletResponse response, ZbEmployCommentQueryCriteria criteria) throws IOException {
        zbEmployCommentService.download(generator.convert(zbEmployCommentService.queryAll(criteria), ZbEmployCommentDto.class), response);
    }

    @GetMapping
    @Log("查询评价")
    @ApiOperation("查询评价")
    @PreAuthorize("@el.check('admin','zbEmployComment:list')")
    public ResponseEntity<Object> getZbEmployComments(ZbEmployCommentQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbEmployCommentService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增评价")
    @ApiOperation("新增评价")
    @PreAuthorize("@el.check('admin','zbEmployComment:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbEmployComment resources){
        return new ResponseEntity<>(zbEmployCommentService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改评价")
    @ApiOperation("修改评价")
    @PreAuthorize("@el.check('admin','zbEmployComment:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbEmployComment resources){
        zbEmployCommentService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除评价")
    @ApiOperation("删除评价")
    @PreAuthorize("@el.check('admin','zbEmployComment:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbEmployCommentService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
