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
import co.zhenxi.modules.shop.domain.ZbUnionRights;
import co.zhenxi.modules.shop.service.ZbUnionRightsService;
import co.zhenxi.modules.shop.service.dto.ZbUnionRightsDto;
import co.zhenxi.modules.shop.service.dto.ZbUnionRightsQueryCriteria;
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
* @date 2020-07-30
*/
@AllArgsConstructor
@Api(tags = "订单维权管理")
@RestController
@RequestMapping("/api/zbUnionRights")
public class ZbUnionRightsController {

    private final ZbUnionRightsService zbUnionRightsService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbUnionRights:list')")
    public void download(HttpServletResponse response, ZbUnionRightsQueryCriteria criteria) throws IOException {
        zbUnionRightsService.download(generator.convert(zbUnionRightsService.queryAll(criteria), ZbUnionRightsDto.class), response);
    }

    @GetMapping
    @Log("查询订单维权")
    @ApiOperation("查询订单维权")
    @PreAuthorize("@el.check('admin','zbUnionRights:list')")
    public ResponseEntity<Object> getZbUnionRightss(ZbUnionRightsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbUnionRightsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增订单维权")
    @ApiOperation("新增订单维权")
    @PreAuthorize("@el.check('admin','zbUnionRights:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbUnionRights resources){
        return new ResponseEntity<>(zbUnionRightsService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改订单维权")
    @ApiOperation("修改订单维权")
    @PreAuthorize("@el.check('admin','zbUnionRights:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbUnionRights resources){
        zbUnionRightsService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除订单维权")
    @ApiOperation("删除订单维权")
    @PreAuthorize("@el.check('admin','zbUnionRights:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbUnionRightsService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/ShopRightsList")
    @Log("查询交易维权")
    @ApiOperation("查询交易维权")
    //@PreAuthorize("@el.check('admin','zbUnionRights:list')")
    @AnonymousAccess
    public ResponseEntity<Object> ShopRightsList(ZbUnionRightsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbUnionRightsService.queryAll(criteria,pageable),HttpStatus.OK);
    }


}
