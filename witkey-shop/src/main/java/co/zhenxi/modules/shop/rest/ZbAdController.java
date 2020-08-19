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
import co.zhenxi.modules.shop.domain.ZbAd;
import co.zhenxi.modules.shop.service.ZbAdService;
import co.zhenxi.modules.shop.service.dto.ZbAdDto;
import co.zhenxi.modules.shop.service.dto.ZbAdQueryCriteria;
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
@Api(tags = "广告管理")
@RestController
@RequestMapping("/api/zbAd")
public class ZbAdController {

    private final ZbAdService zbAdService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    //@PreAuthorize("@el.check('admin','zbAd:list')")
    @AnonymousAccess
    public void download(HttpServletResponse response, ZbAdQueryCriteria criteria) throws IOException {
        zbAdService.download(generator.convert(zbAdService.queryAll(criteria), ZbAdDto.class), response);
    }

    @GetMapping
    @Log("查询广告")
    @ApiOperation("查询广告")
    @PreAuthorize("@el.check('admin','zbAd:list')")
    public ResponseEntity<Object> getZbAds(ZbAdQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbAdService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增广告")
    @ApiOperation("新增广告")
    @PreAuthorize("@el.check('admin','zbAd:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbAd resources){
        return new ResponseEntity<>(zbAdService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改广告")
    @ApiOperation("修改广告")
    @PreAuthorize("@el.check('admin','zbAd:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbAd resources){
        zbAdService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除广告")
    @ApiOperation("删除广告")
    @PreAuthorize("@el.check('admin','zbAd:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbAdService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
