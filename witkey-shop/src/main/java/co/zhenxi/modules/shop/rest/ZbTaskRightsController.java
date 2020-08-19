/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbTaskRights;
import co.zhenxi.modules.shop.service.ZbTaskRightsService;
import co.zhenxi.modules.shop.service.dto.ZbTaskRightsDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskRightsQueryCriteria;
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
@Api(tags = "维权表管理")
@RestController
@RequestMapping("/api/zbTaskRights")
public class ZbTaskRightsController {

    private final ZbTaskRightsService zbTaskRightsService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbTaskRights:list')")
    public void download(HttpServletResponse response, ZbTaskRightsQueryCriteria criteria) throws IOException {
        zbTaskRightsService.download(generator.convert(zbTaskRightsService.queryAll(criteria), ZbTaskRightsDto.class), response);
    }

    @GetMapping
    @Log("查询维权表")
    @ApiOperation("查询维权表")
    @PreAuthorize("@el.check('admin','zbTaskRights:list')")
    public ResponseEntity<Object> getZbTaskRightss(ZbTaskRightsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTaskRightsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增维权表")
    @ApiOperation("新增维权表")
    @PreAuthorize("@el.check('admin','zbTaskRights:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbTaskRights resources){
        return new ResponseEntity<>(zbTaskRightsService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改维权表")
    @ApiOperation("修改维权表")
    @PreAuthorize("@el.check('admin','zbTaskRights:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbTaskRights resources){
        zbTaskRightsService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除维权表")
    @ApiOperation("删除维权表")
    @PreAuthorize("@el.check('admin','zbTaskRights:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbTaskRightsService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
