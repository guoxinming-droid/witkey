/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbEmployWork;
import co.zhenxi.modules.shop.service.ZbEmployWorkService;
import co.zhenxi.modules.shop.service.dto.ZbEmployWorkDto;
import co.zhenxi.modules.shop.service.dto.ZbEmployWorkQueryCriteria;
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
* @date 2020-08-21
*/
@AllArgsConstructor
@Api(tags = "交稿记录管理")
@RestController
@RequestMapping("/api/zbEmployWork")
public class ZbEmployWorkController {

    private final ZbEmployWorkService zbEmployWorkService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbEmployWork:list')")
    public void download(HttpServletResponse response, ZbEmployWorkQueryCriteria criteria) throws IOException {
        zbEmployWorkService.download(generator.convert(zbEmployWorkService.queryAll(criteria), ZbEmployWorkDto.class), response);
    }

    @GetMapping
    @Log("查询交稿记录")
    @ApiOperation("查询交稿记录")
    @PreAuthorize("@el.check('admin','zbEmployWork:list')")
    public ResponseEntity<Object> getZbEmployWorks(ZbEmployWorkQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbEmployWorkService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增交稿记录")
    @ApiOperation("新增交稿记录")
    @PreAuthorize("@el.check('admin','zbEmployWork:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbEmployWork resources){
        return new ResponseEntity<>(zbEmployWorkService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改交稿记录")
    @ApiOperation("修改交稿记录")
    @PreAuthorize("@el.check('admin','zbEmployWork:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbEmployWork resources){
        zbEmployWorkService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除交稿记录")
    @ApiOperation("删除交稿记录")
    @PreAuthorize("@el.check('admin','zbEmployWork:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbEmployWorkService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
