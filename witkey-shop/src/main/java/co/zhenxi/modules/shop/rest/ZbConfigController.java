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
import co.zhenxi.modules.shop.domain.ZbConfig;
import co.zhenxi.modules.shop.service.ZbConfigService;
import co.zhenxi.modules.shop.service.dto.ZbConfigDto;
import co.zhenxi.modules.shop.service.dto.ZbConfigQueryCriteria;
import co.zhenxi.tools.service.LocalStorageService;
import co.zhenxi.tools.service.PictureService;
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
* @date 2020-07-17
*/
@AllArgsConstructor
@Api(tags = "配置管理")
@RestController
@RequestMapping("/api/zbConfig")
public class ZbConfigController {



    private final ZbConfigService zbConfigService;
    private final IGenerator generator;
    private final PictureService pictureService;
    private final LocalStorageService localStorageService;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbConfig:list')")
    public void download(HttpServletResponse response, ZbConfigQueryCriteria criteria) throws IOException {
        zbConfigService.download(generator.convert(zbConfigService.queryAll(criteria), ZbConfigDto.class), response);
    }

    @GetMapping
    @Log("查询配置")
    @ApiOperation("查询配置")
    @PreAuthorize("@el.check('admin','zbConfig:list')")
    public ResponseEntity<Object> getZbConfigs(ZbConfigQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbConfigService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增配置")
    @ApiOperation("新增配置")
    @PreAuthorize("@el.check('admin','zbConfig:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbConfig resources){
        return new ResponseEntity<>(zbConfigService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改配置")
    @ApiOperation("修改配置")
    @PreAuthorize("@el.check('admin','zbConfig:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbConfig resources){
        zbConfigService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除配置")
    @ApiOperation("删除配置")
    @PreAuthorize("@el.check('admin','zbConfig:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbConfigService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }




    @GetMapping("/getZbSite")
    @Log("查询站点配置")
    @ApiOperation("查询站点配置")
  //  @PreAuthorize("@el.check('admin','zbConfig:list')")
    @AnonymousAccess
    @ResponseBody
    public List<ZbConfig> getZbSite(@RequestParam String type){
        return zbConfigService.getZbSiteBy(type);
    }



    @PutMapping("/updateSite")
    @Log("修改站点配置")
    @ApiOperation("修改站点配置")
    @AnonymousAccess
    public ResponseEntity<Object> updateSite(@Validated  List<ZbConfig> zbConfig){
        for(ZbConfig  Config:zbConfig) {
            zbConfigService.updateById(Config);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
