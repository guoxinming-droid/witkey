/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbUserFocus;
import co.zhenxi.modules.shop.service.ZbUserFocusService;
import co.zhenxi.modules.shop.service.dto.ZbUserFocusDto;
import co.zhenxi.modules.shop.service.dto.ZbUserFocusQueryCriteria;
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
* @date 2020-08-19
*/
@AllArgsConstructor
@Api(tags = "关注表管理")
@RestController
@RequestMapping("/api/zbUserFocus")
public class ZbUserFocusController {

    private final ZbUserFocusService zbUserFocusService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbUserFocus:list')")
    public void download(HttpServletResponse response, ZbUserFocusQueryCriteria criteria) throws IOException {
        zbUserFocusService.download(generator.convert(zbUserFocusService.queryAll(criteria), ZbUserFocusDto.class), response);
    }

    @GetMapping
    @Log("查询关注表")
    @ApiOperation("查询关注表")
    @PreAuthorize("@el.check('admin','zbUserFocus:list')")
    public ResponseEntity<Object> getZbUserFocuss(ZbUserFocusQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbUserFocusService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增关注")
    @ApiOperation("新增关注")
    @PreAuthorize("@el.check('admin','zbUserFocus:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbUserFocus resources){
        return new ResponseEntity<>(zbUserFocusService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改关注表")
    @ApiOperation("修改关注表")
    @PreAuthorize("@el.check('admin','zbUserFocus:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbUserFocus resources){
        zbUserFocusService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除关注表")
    @ApiOperation("删除关注表")
    @PreAuthorize("@el.check('admin','zbUserFocus:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbUserFocusService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
