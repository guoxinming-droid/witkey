/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbUsers;
import co.zhenxi.modules.shop.service.ZbUsersService;
import co.zhenxi.modules.shop.service.dto.ZbUsersDto;
import co.zhenxi.modules.shop.service.dto.ZbUsersQueryCriteria;
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
* @date 2020-07-22
*/
@AllArgsConstructor
@Api(tags = "商城用户管理")
@RestController
@RequestMapping("/api/zbUsers")
public class ZbUsersController {

    private final ZbUsersService zbUsersService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbUsers:list')")
    public void download(HttpServletResponse response, ZbUsersQueryCriteria criteria) throws IOException {
        zbUsersService.download(generator.convert(zbUsersService.queryAll(criteria), ZbUsersDto.class), response);
    }

    @GetMapping
    @Log("查询商城用户")
    @ApiOperation("查询商城用户")
    @PreAuthorize("@el.check('admin','zbUsers:list')")
    public ResponseEntity<Object> getZbUserss(ZbUsersQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbUsersService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增商城用户")
    @ApiOperation("新增商城用户")
    @PreAuthorize("@el.check('admin','zbUsers:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbUsers resources){
        return new ResponseEntity<>(zbUsersService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改商城用户")
    @ApiOperation("修改商城用户")
    @PreAuthorize("@el.check('admin','zbUsers:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbUsers resources){
        zbUsersService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除商城用户")
    @ApiOperation("删除商城用户")
    @PreAuthorize("@el.check('admin','zbUsers:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbUsersService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
