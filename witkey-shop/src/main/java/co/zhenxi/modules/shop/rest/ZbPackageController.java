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
import co.zhenxi.modules.shop.domain.ZbPackage;
import co.zhenxi.modules.shop.service.ZbPackageService;
import co.zhenxi.modules.shop.service.dto.ZbPackageDto;
import co.zhenxi.modules.shop.service.dto.ZbPackageQueryCriteria;
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
* @author Guoxm
* @date 2020-07-16
*/
@AllArgsConstructor
@Api(tags = "套餐管理")
@RestController
@RequestMapping("/api/zbPackage")
public class ZbPackageController {

    private final ZbPackageService zbPackageService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbPackage:list')")
    public void download(HttpServletResponse response, ZbPackageQueryCriteria criteria) throws IOException {
        zbPackageService.download(generator.convert(zbPackageService.queryAll(criteria), ZbPackageDto.class), response);
    }

    @GetMapping
    @Log("查询套餐")
    @ApiOperation("查询套餐")
    @PreAuthorize("@el.check('admin','zbPackage:list')")
    public ResponseEntity<Object> getZbPackages(ZbPackageQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbPackageService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增套餐")
    @ApiOperation("新增套餐")
    @PreAuthorize("@el.check('admin','zbPackage:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbPackage resources){
        return new ResponseEntity<>(zbPackageService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改套餐")
    @ApiOperation("修改套餐")
    @PreAuthorize("@el.check('admin','zbPackage:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbPackage resources){
        zbPackageService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除套餐")
    @ApiOperation("删除套餐")
    @PreAuthorize("@el.check('admin','zbPackage:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbPackageService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("套餐上下架")
    @ApiOperation(value = "套餐上下架")
    @PostMapping(value = "/onStatus")
    @AnonymousAccess
    public ResponseEntity onStatus(@RequestParam Integer id,@RequestParam Integer status){
        zbPackageService.onStatus(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }


}
