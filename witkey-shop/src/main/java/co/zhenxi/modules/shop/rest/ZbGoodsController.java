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
import co.zhenxi.modules.shop.domain.ZbGoods;
import co.zhenxi.modules.shop.service.ZbConfigService;
import co.zhenxi.modules.shop.service.ZbGoodsService;
import co.zhenxi.modules.shop.service.dto.ZbGoodsDto;
import co.zhenxi.modules.shop.service.dto.ZbGoodsQueryCriteria;
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
* @date 2020-07-29
*/
@AllArgsConstructor
@Api(tags = "商品管理管理")
@RestController
@RequestMapping("/api/zbGoods")
public class ZbGoodsController {

    private final ZbGoodsService zbGoodsService;
    private final IGenerator generator;
    private final ZbConfigService zbConfigService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbGoods:list')")
    public void download(HttpServletResponse response, ZbGoodsQueryCriteria criteria) throws IOException {
        zbGoodsService.download(generator.convert(zbGoodsService.queryAll(criteria), ZbGoodsDto.class), response);
    }

    @GetMapping
    @Log("查询商品管理")
    @ApiOperation("查询商品管理")
    @PreAuthorize("@el.check('admin','zbGoods:list')")
    public ResponseEntity<Object> getZbGoodss(ZbGoodsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbGoodsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增商品管理")
    @ApiOperation("新增商品管理")
    @PreAuthorize("@el.check('admin','zbGoods:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbGoods resources){
        return new ResponseEntity<>(zbGoodsService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改商品管理")
    @ApiOperation("修改商品管理")
    @PreAuthorize("@el.check('admin','zbGoods:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbGoods resources){
        zbGoodsService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除商品管理")
    @ApiOperation("删除商品管理")
    @PreAuthorize("@el.check('admin','zbGoods:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbGoodsService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/goodsServiceList")
    @Log("查询商品管理")
    @ApiOperation("查询商品管理")
    //@PreAuthorize("@el.check('admin','zbGoods:list')")
    @AnonymousAccess
    public ResponseEntity<Object> goodsServiceList(ZbGoodsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbGoodsService.goodsServiceList(criteria,pageable),HttpStatus.OK);
    }

    @GetMapping("/goodsList")
    @Log("查询作品管理")
    @ApiOperation("查询作品管理")
    //@PreAuthorize("@el.check('admin','zbGoods:list')")
    @AnonymousAccess
    public ResponseEntity<Object> goodsList(ZbGoodsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbGoodsService.goodsList(criteria,pageable),HttpStatus.OK);
    }

    @GetMapping("/serviceConfig")
    @Log("查询威客服务流程配置")
    @ApiOperation("查询威客服务流程配置")
    //  @PreAuthorize("@el.check('admin','zbConfig:list')")
    @AnonymousAccess
    @ResponseBody
    public List<ZbConfig> serviceConfig(){
        String type = "employ";
        return zbConfigService.getZbSiteBy(type);
    }



    @PutMapping("/serviceConfigUpdate")
    @Log("修改威客服务流程配置")
    @ApiOperation("修改威客服务流程配置")
    @AnonymousAccess
    public ResponseEntity<Object> serviceConfigUpdate(@Validated  List<ZbConfig> zbConfig){
        for(ZbConfig  Config:zbConfig) {
            zbConfigService.updateById(Config);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/goodsConfig")
    @Log("查询作品流程配置")
    @ApiOperation("查询作品流程配置")
    //  @PreAuthorize("@el.check('admin','zbConfig:list')")
    @AnonymousAccess
    @ResponseBody
    public List<ZbConfig> goodsConfig(){
        String type = "goods_config";
        return zbConfigService.getZbSiteBy(type);
    }



    @PutMapping("/postGoodsConfig")
    @Log("修改查询作品流程配置")
    @ApiOperation("修改查询作品流程配置")
    @AnonymousAccess
    public ResponseEntity<Object> postGoodsConfig(@Validated  List<ZbConfig> zbConfig){
        for(ZbConfig  Config:zbConfig) {
            zbConfigService.updateById(Config);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
