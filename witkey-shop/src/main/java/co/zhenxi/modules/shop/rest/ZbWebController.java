/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbWeb;
import co.zhenxi.modules.shop.service.ZbWebService;
import co.zhenxi.modules.shop.service.dto.ZbWebDto;
import co.zhenxi.modules.shop.service.dto.ZbWebQueryCriteria;
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
* @author Guo xinming
* @date 2020-07-16
*/
@AllArgsConstructor
@Api(tags = "网站地图管理")
@RestController
@RequestMapping("/api/zbWeb")
public class ZbWebController {

    private final ZbWebService zbWebService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbWeb:list')")
    public void download(HttpServletResponse response, ZbWebQueryCriteria criteria) throws IOException {
        zbWebService.download(generator.convert(zbWebService.queryAll(criteria), ZbWebDto.class), response);
    }

    @GetMapping
    @Log("查询网站地图")
    @ApiOperation("查询网站地图")
    @PreAuthorize("@el.check('admin','zbWeb:list')")
    public ResponseEntity<Object> getZbWebs(ZbWebQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbWebService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增网站地图")
    @ApiOperation("新增网站地图")
    @PreAuthorize("@el.check('admin','zbWeb:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbWeb resources){
        return new ResponseEntity<>(zbWebService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改网站地图")
    @ApiOperation("修改网站地图")
    @PreAuthorize("@el.check('admin','zbWeb:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbWeb resources){
        zbWebService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除网站地图")
    @ApiOperation("删除网站地图")
    @PreAuthorize("@el.check('admin','zbWeb:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbWebService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
