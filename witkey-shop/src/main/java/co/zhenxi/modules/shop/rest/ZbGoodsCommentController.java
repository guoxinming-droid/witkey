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
import co.zhenxi.modules.shop.domain.ZbGoodsComment;
import co.zhenxi.modules.shop.service.ZbGoodsCommentService;
import co.zhenxi.modules.shop.service.dto.ZbGoodsCommentDto;
import co.zhenxi.modules.shop.service.dto.ZbGoodsCommentQueryCriteria;
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
@Api(tags = "商品评价管理")
@RestController
@RequestMapping("/api/zbGoodsComment")
public class ZbGoodsCommentController {

    private final ZbGoodsCommentService zbGoodsCommentService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    //@PreAuthorize("@el.check('admin','zbGoodsComment:list')")
    @AnonymousAccess
    public void download(HttpServletResponse response, ZbGoodsCommentQueryCriteria criteria) throws IOException {
        zbGoodsCommentService.download(generator.convert(zbGoodsCommentService.queryAll(criteria), ZbGoodsCommentDto.class), response);
    }

    @GetMapping
    @Log("查询商品评价")
    @ApiOperation("查询商品评价")
    //@PreAuthorize("@el.check('admin','zbGoodsComment:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getZbGoodsComments(ZbGoodsCommentQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbGoodsCommentService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增商品评价")
    @ApiOperation("新增商品评价")
    @PreAuthorize("@el.check('admin','zbGoodsComment:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbGoodsComment resources){
        return new ResponseEntity<>(zbGoodsCommentService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改商品评价")
    @ApiOperation("修改商品评价")
    @PreAuthorize("@el.check('admin','zbGoodsComment:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbGoodsComment resources){
        zbGoodsCommentService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除商品评价")
    @ApiOperation("删除商品评价")
    @PreAuthorize("@el.check('admin','zbGoodsComment:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbGoodsCommentService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
