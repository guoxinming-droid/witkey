/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbWebCate;
import co.zhenxi.modules.shop.service.ZbWebCateService;
import co.zhenxi.modules.shop.service.dto.ZbWebCateDto;
import co.zhenxi.modules.shop.service.dto.ZbWebCateQueryCriteria;
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
* @author Guo xinming
* @date 2020-07-16
*/
@AllArgsConstructor
@Api(tags = "栏目分类管理")
@RestController
@RequestMapping("/api/zbWebCate")
public class ZbWebCateController {

    private final ZbWebCateService zbWebCateService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbWebCate:list')")
    public void download(HttpServletResponse response, ZbWebCateQueryCriteria criteria) throws IOException {
        zbWebCateService.download(generator.convert(zbWebCateService.queryAll(criteria), ZbWebCateDto.class), response);
    }

    @GetMapping
    @Log("查询栏目分类")
    @ApiOperation("查询栏目分类")
    @PreAuthorize("@el.check('admin','zbWebCate:list')")
    public ResponseEntity<Object> getZbWebCates(ZbWebCateQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbWebCateService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增栏目分类")
    @ApiOperation("新增栏目分类")
    @PreAuthorize("@el.check('admin','zbWebCate:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbWebCate resources){
        return new ResponseEntity<>(zbWebCateService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改栏目分类")
    @ApiOperation("修改栏目分类")
    @PreAuthorize("@el.check('admin','zbWebCate:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbWebCate resources){
        zbWebCateService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除栏目分类")
    @ApiOperation("删除栏目分类")
    @PreAuthorize("@el.check('admin','zbWebCate:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbWebCateService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
