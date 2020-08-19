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
import co.zhenxi.modules.shop.domain.ZbEmploy;
import co.zhenxi.modules.shop.service.ZbConfigService;
import co.zhenxi.modules.shop.service.ZbEmployService;
import co.zhenxi.modules.shop.service.dto.ZbEmployDto;
import co.zhenxi.modules.shop.service.dto.ZbEmployQueryCriteria;
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
* @author guoke
* @date 2020-07-27
*/
@AllArgsConstructor
@Api(tags = "雇佣管理管理")
@RestController
@RequestMapping("/api/zbEmploy")
public class ZbEmployController {

    private final ZbEmployService zbEmployService;
    private final IGenerator generator;
    private final ZbConfigService zbConfigService;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbEmploy:list')")
    public void download(HttpServletResponse response, ZbEmployQueryCriteria criteria) throws IOException {
        zbEmployService.download(generator.convert(zbEmployService.queryAll(criteria), ZbEmployDto.class), response);
    }

    @GetMapping
    @Log("查询雇佣管理")
    @ApiOperation("查询雇佣管理")
    @PreAuthorize("@el.check('admin','zbEmploy:list')")
    public ResponseEntity<Object> getZbEmploys(ZbEmployQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbEmployService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增雇佣管理")
    @ApiOperation("新增雇佣管理")
    @PreAuthorize("@el.check('admin','zbEmploy:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbEmploy resources){
        return new ResponseEntity<>(zbEmployService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改雇佣管理")
    @ApiOperation("修改雇佣管理")
    @PreAuthorize("@el.check('admin','zbEmploy:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbEmploy resources){
        zbEmployService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除雇佣管理")
    @ApiOperation("删除雇佣管理")
    @PreAuthorize("@el.check('admin','zbEmploy:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbEmployService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getVipLogo")
    @Log("查询雇佣配置")
    @ApiOperation("查询雇佣配置")
    //  @PreAuthorize("@el.check('admin','zbConfig:list')")
    @AnonymousAccess
    @ResponseBody
    public List<ZbConfig> getVipLogo(@RequestParam String type){
        return zbConfigService.getZbSiteBy(type);
    }



    @PutMapping("/updateVipLogo")
    @Log("修改雇佣配置")
    @ApiOperation("修改雇佣配置")
    @AnonymousAccess
    public ResponseEntity<Object> updateVipLogo(@Validated  List<ZbConfig> zbConfig){
        for(ZbConfig  Config:zbConfig) {
            zbConfigService.updateById(Config);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/serviceOrderList")
    @Log("查询威客服务订单管理")
    @ApiOperation("查询威客服务订单管理")
    @PreAuthorize("@el.check('admin','zbEmploy:list')")
    public ResponseEntity<Object> serviceOrderList(ZbEmployQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbEmployService.serviceOrderList(criteria,pageable),HttpStatus.OK);
    }


}
