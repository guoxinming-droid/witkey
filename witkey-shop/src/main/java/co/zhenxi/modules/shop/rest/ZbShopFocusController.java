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
import co.zhenxi.modules.shop.domain.ZbShopFocus;
import co.zhenxi.modules.shop.service.ZbShopFocusService;
import co.zhenxi.modules.shop.service.dto.ZbShopFocusDto;
import co.zhenxi.modules.shop.service.dto.ZbShopFocusQueryCriteria;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author guoke
* @date 2020-08-19
*/
@AllArgsConstructor
@Api(tags = "商城关注表管理")
@RestController
@RequestMapping("/api/zbShopFocus")
public class ZbShopFocusController {

    private final ZbShopFocusService zbShopFocusService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbShopFocus:list')")
    public void download(HttpServletResponse response, ZbShopFocusQueryCriteria criteria) throws IOException {
        zbShopFocusService.download(generator.convert(zbShopFocusService.queryAll(criteria), ZbShopFocusDto.class), response);
    }

    @GetMapping
    @Log("查询商城关注表")
    @ApiOperation("查询商城关注表")
    @PreAuthorize("@el.check('admin','zbShopFocus:list')")
    public ResponseEntity<Object> getZbShopFocuss(ZbShopFocusQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbShopFocusService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping("/create")
    @Log("关注商城")
    @ApiOperation("关注商城")
    //@PreAuthorize("@el.check('admin','zbShopFocus:add')")
    @AnonymousAccess
    public ResponseEntity<Object> create( ZbShopFocus resources){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resources.setCreatedAt(Timestamp.valueOf(df.format(new Date())));
        return new ResponseEntity<>(zbShopFocusService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改商城关注表")
    @ApiOperation("修改商城关注表")
    @PreAuthorize("@el.check('admin','zbShopFocus:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbShopFocus resources){
        zbShopFocusService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("取消商城关注")
    @ApiOperation("取消商城关注")
    //@PreAuthorize("@el.check('admin','zbShopFocus:del')")
    @DeleteMapping("delete")
    public ResponseEntity<Object> deleteAll( Integer uid,Integer shopId) {
            zbShopFocusService.removeByUid(uid,shopId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
