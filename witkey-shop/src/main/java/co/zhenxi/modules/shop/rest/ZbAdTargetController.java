/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbAdTarget;
import co.zhenxi.modules.shop.service.ZbAdTargetService;
import co.zhenxi.modules.shop.service.dto.ZbAdTargetDto;
import co.zhenxi.modules.shop.service.dto.ZbAdTargetQueryCriteria;
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
@Api(tags = "广告位管理")
@RestController
@RequestMapping("/api/zbAdTarget")
public class ZbAdTargetController {

    private final ZbAdTargetService zbAdTargetService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbAdTarget:list')")
    public void download(HttpServletResponse response, ZbAdTargetQueryCriteria criteria) throws IOException {
        zbAdTargetService.download(generator.convert(zbAdTargetService.queryAll(criteria), ZbAdTargetDto.class), response);
    }

    @GetMapping
    @Log("查询广告位")
    @ApiOperation("查询广告位")
    @PreAuthorize("@el.check('admin','zbAdTarget:list')")
    public ResponseEntity<Object> getZbAdTargets(ZbAdTargetQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbAdTargetService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增广告位")
    @ApiOperation("新增广告位")
    @PreAuthorize("@el.check('admin','zbAdTarget:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbAdTarget resources){
        return new ResponseEntity<>(zbAdTargetService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改广告位")
    @ApiOperation("修改广告位")
    @PreAuthorize("@el.check('admin','zbAdTarget:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbAdTarget resources){
        zbAdTargetService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除广告位")
    @ApiOperation("删除广告位")
    @PreAuthorize("@el.check('admin','zbAdTarget:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbAdTargetService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
