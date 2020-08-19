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
import co.zhenxi.modules.shop.domain.ZbEnterpriseAuth;
import co.zhenxi.modules.shop.service.ZbEnterpriseAuthService;
import co.zhenxi.modules.shop.service.dto.ZbEnterpriseAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbEnterpriseAuthQueryCriteria;
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
* @date 2020-08-05
*/
@AllArgsConstructor
@Api(tags = "企业认证管理")
@RestController
@RequestMapping("/api/zbEnterpriseAuth")
public class ZbEnterpriseAuthController {

    private final ZbEnterpriseAuthService zbEnterpriseAuthService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbEnterpriseAuth:list')")
    public void download(HttpServletResponse response, ZbEnterpriseAuthQueryCriteria criteria) throws IOException {
        zbEnterpriseAuthService.download(generator.convert(zbEnterpriseAuthService.queryAll(criteria), ZbEnterpriseAuthDto.class), response);
    }

    @GetMapping
    @Log("查询企业认证")
    @ApiOperation("查询企业认证")
    @PreAuthorize("@el.check('admin','zbEnterpriseAuth:list')")
    public ResponseEntity<Object> getZbEnterpriseAuths(ZbEnterpriseAuthQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbEnterpriseAuthService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增企业认证")
    @ApiOperation("新增企业认证")
    @PreAuthorize("@el.check('admin','zbEnterpriseAuth:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbEnterpriseAuth resources){
        return new ResponseEntity<>(zbEnterpriseAuthService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改企业认证")
    @ApiOperation("修改企业认证")
    @PreAuthorize("@el.check('admin','zbEnterpriseAuth:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbEnterpriseAuth resources){
        zbEnterpriseAuthService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除企业认证")
    @ApiOperation("删除企业认证")
    @PreAuthorize("@el.check('admin','zbEnterpriseAuth:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbEnterpriseAuthService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/enterpriseAuthList")
    @Log("查询企业认证")
    @ApiOperation("查询企业认证")
    @PreAuthorize("@el.check('admin','zbEnterpriseAuth:list')")
    public ResponseEntity<Object> enterpriseAuthList(ZbEnterpriseAuthQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbEnterpriseAuthService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("企业认证审核")
    @ApiOperation(value = "企业认证审核")
    @PostMapping(value = "/onStatus")
    @AnonymousAccess
    public ResponseEntity onStatus(@RequestParam Integer id,@RequestParam Integer status){
        zbEnterpriseAuthService.onStatus(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }

}
