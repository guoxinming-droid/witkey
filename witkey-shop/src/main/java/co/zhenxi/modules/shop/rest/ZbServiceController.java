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
import co.zhenxi.modules.shop.domain.ZbService;
import co.zhenxi.modules.shop.service.ZbServiceService;
import co.zhenxi.modules.shop.service.dto.ZbServiceDto;
import co.zhenxi.modules.shop.service.dto.ZbServiceQueryCriteria;
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
* @date 2020-07-28
*/
@AllArgsConstructor
@Api(tags = "服务管理管理")
@RestController
@RequestMapping("/api/zbService")
public class ZbServiceController {

    private final ZbServiceService zbServiceService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbService:list')")
    public void download(HttpServletResponse response, ZbServiceQueryCriteria criteria) throws IOException {
        zbServiceService.download(generator.convert(zbServiceService.queryAll(criteria), ZbServiceDto.class), response);
    }

    @GetMapping
    @Log("查询服务管理")
    @ApiOperation("查询服务管理")
    @PreAuthorize("@el.check('admin','zbService:list')")
    public ResponseEntity<Object> getZbServices(ZbServiceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbServiceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增服务管理")
    @ApiOperation("新增服务管理")
    @PreAuthorize("@el.check('admin','zbService:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbService resources){
        return new ResponseEntity<>(zbServiceService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改服务管理")
    @ApiOperation("修改服务管理")
    @PreAuthorize("@el.check('admin','zbService:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbService resources){
        zbServiceService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除服务管理")
    @ApiOperation("删除服务管理")
    @PreAuthorize("@el.check('admin','zbService:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbServiceService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/serviceList")
    @Log("查询服务管理")
    @ApiOperation("查询服务管理")
    //@PreAuthorize("@el.check('admin','zbService:list')")
    @AnonymousAccess
    public ResponseEntity<Object> serviceList(ZbServiceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbServiceService.serviceList(criteria,pageable),HttpStatus.OK);
    }
}
