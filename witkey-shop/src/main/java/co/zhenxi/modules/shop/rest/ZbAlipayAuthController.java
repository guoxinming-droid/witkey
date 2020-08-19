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
import co.zhenxi.modules.shop.domain.ZbAlipayAuth;
import co.zhenxi.modules.shop.service.ZbAlipayAuthService;
import co.zhenxi.modules.shop.service.dto.ZbAlipayAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbAlipayAuthQueryCriteria;
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
* @author Gxm
* @date 2020-07-16
*/
@AllArgsConstructor
@Api(tags = "alipayAuth管理")
@RestController
@RequestMapping("/api/zbAlipayAuth")
public class ZbAlipayAuthController {

    private final ZbAlipayAuthService zbAlipayAuthService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbAlipayAuth:list')")
    public void download(HttpServletResponse response, ZbAlipayAuthQueryCriteria criteria) throws IOException {
        zbAlipayAuthService.download(generator.convert(zbAlipayAuthService.queryAll(criteria), ZbAlipayAuthDto.class), response);
    }

    @GetMapping
    @Log("查询alipayAuth")
    @ApiOperation("查询alipayAuth")
    @PreAuthorize("@el.check('admin','zbAlipayAuth:list')")
    public ResponseEntity<Object> getZbAlipayAuths(ZbAlipayAuthQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbAlipayAuthService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增alipayAuth")
    @ApiOperation("新增alipayAuth")
    @PreAuthorize("@el.check('admin','zbAlipayAuth:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbAlipayAuth resources){
        return new ResponseEntity<>(zbAlipayAuthService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改alipayAuth")
    @ApiOperation("修改alipayAuth")
    @PreAuthorize("@el.check('admin','zbAlipayAuth:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbAlipayAuth resources){
        zbAlipayAuthService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除alipayAuth")
    @ApiOperation("删除alipayAuth")
    @PreAuthorize("@el.check('admin','zbAlipayAuth:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbAlipayAuthService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/alipayAuthList")
    @Log("查询alipayAuth")
    @ApiOperation("查询alipayAuth")
    //@PreAuthorize("@el.check('admin','zbAlipayAuth:list')")
    @AnonymousAccess
    public ResponseEntity<Object> alipayAuthList(ZbAlipayAuthQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbAlipayAuthService.alipayAuthList(criteria,pageable),HttpStatus.OK);
    }

    @Log("支付宝认证审核")
    @ApiOperation(value = "支付宝认证审核")
    @PostMapping(value = "/onStatus")
    @AnonymousAccess
    public ResponseEntity onStatus(@RequestParam Integer id,@RequestParam Integer status){
        zbAlipayAuthService.onStatus(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }
}
