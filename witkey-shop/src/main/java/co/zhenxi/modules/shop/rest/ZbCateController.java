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
import co.zhenxi.modules.shop.domain.ZbCate;
import co.zhenxi.modules.shop.service.ZbCateService;
import co.zhenxi.modules.shop.service.dto.ZbCateDto;
import co.zhenxi.modules.shop.service.dto.ZbCateQueryCriteria;
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
import java.util.List;

/**
* @author Guoxm
* @date 2020-07-20
*/
@AllArgsConstructor
@Api(tags = "行业管理管理")
@RestController
@RequestMapping("/api/zbCate")
public class ZbCateController {

    private final ZbCateService zbCateService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbCate:list')")
    public void download(HttpServletResponse response, ZbCateQueryCriteria criteria) throws IOException {
        zbCateService.download(generator.convert(zbCateService.queryAll(criteria), ZbCateDto.class), response);
    }

    @GetMapping
    @Log("查询行业管理")
    @ApiOperation("查询行业管理")
    @PreAuthorize("@el.check('admin','zbCate:list')")
    public ResponseEntity<Object> getZbCates(ZbCateQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbCateService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增行业管理")
    @ApiOperation("新增行业管理")
    @PreAuthorize("@el.check('admin','zbCate:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbCate resources){
        return new ResponseEntity<>(zbCateService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改行业管理")
    @ApiOperation("修改行业管理")
    @PreAuthorize("@el.check('admin','zbCate:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbCate resources){
        zbCateService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除行业管理")
    @ApiOperation("删除行业管理")
    @PreAuthorize("@el.check('admin','zbCate:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbCateService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public void init(){
        List<ZbCate> cateList = zbCateService.getByFid(0);
    }


    //小程序接口

    @GetMapping("/getZbCatesList")
    @Log("查询行业")
    @ApiOperation("查询行业")
    //@PreAuthorize("@el.check('admin','zbCate:list')")
    @AnonymousAccess
    public List<ZbCate> getZbCatesList(Integer fid){
        return zbCateService.getZbCatesList(fid);
    }


}
