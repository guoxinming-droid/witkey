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
import co.zhenxi.modules.shop.domain.ZbShop;
import co.zhenxi.modules.shop.service.ZbConfigService;
import co.zhenxi.modules.shop.service.ZbShopService;
import co.zhenxi.modules.shop.service.dto.ZbShopDto;
import co.zhenxi.modules.shop.service.dto.ZbShopQueryCriteria;
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
* @date 2020-07-16
*/
@AllArgsConstructor
@Api(tags = "店铺管理")
@RestController
@RequestMapping("/api/zbShop")
public class ZbShopController {

    private final ZbShopService zbShopService;
    private final IGenerator generator;
    private final ZbConfigService  zbConfigService;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbShop:list')")
    public void download(HttpServletResponse response, ZbShopQueryCriteria criteria) throws IOException {
        zbShopService.download(generator.convert(zbShopService.queryAll(criteria), ZbShopDto.class), response);
    }

    @GetMapping("/shopList")
    @Log("查询店铺")
    @ApiOperation("查询店铺")
   // @PreAuthorize("@el.check('admin','zbShop:list')")
    @AnonymousAccess
    public ResponseEntity<Object> shopList(ZbShopQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbShopService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增店铺")
    @ApiOperation("新增店铺")
    @PreAuthorize("@el.check('admin','zbShop:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbShop resources){
        return new ResponseEntity<>(zbShopService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改店铺")
    @ApiOperation("修改店铺")
    @PreAuthorize("@el.check('admin','zbShop:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbShop resources){
        zbShopService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除店铺")
    @ApiOperation("删除店铺")
    @PreAuthorize("@el.check('admin','zbShop:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbShopService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/getConfigShop")
    @Log("查询店铺配置")
    @ApiOperation("查询店铺配置")
    //  @PreAuthorize("@el.check('admin','zbConfig:list')")
    @AnonymousAccess
    @ResponseBody
    public List<ZbConfig> getConfigShop(@RequestParam String type){
        return zbConfigService.getZbSiteBy(type);
    }



    @PutMapping("/updateConfigShop")
    @Log("修改店铺配置")
    @ApiOperation("修改店铺配置")
    @AnonymousAccess
    public ResponseEntity<Object> updateConfigShop(@Validated  List<ZbConfig> zbConfig){
        for(ZbConfig  Config:zbConfig) {
            zbConfigService.updateById(Config);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getCollectShop")
    @Log("获取收藏店铺")
    @ApiOperation("获取收藏店铺")
    @AnonymousAccess
    @ResponseBody
    public List getCollectShop(@RequestParam Integer uid){
        return zbShopService.getCollectShop(uid);
    }

}
