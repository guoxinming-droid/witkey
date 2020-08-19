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
import co.zhenxi.modules.shop.domain.ZbBankAuth;
import co.zhenxi.modules.shop.service.ZbBankAuthService;
import co.zhenxi.modules.shop.service.dto.ZbBankAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbBankAuthQueryCriteria;
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
@Api(tags = "bankAuth管理")
@RestController
@RequestMapping("/api/zbBankAuth")
public class ZbBankAuthController {

    private final ZbBankAuthService zbBankAuthService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbBankAuth:list')")
    public void download(HttpServletResponse response, ZbBankAuthQueryCriteria criteria) throws IOException {
        zbBankAuthService.download(generator.convert(zbBankAuthService.queryAll(criteria), ZbBankAuthDto.class), response);
    }

    @GetMapping
    @Log("查询bankAuth")
    @ApiOperation("查询bankAuth")
    @PreAuthorize("@el.check('admin','zbBankAuth:list')")
    public ResponseEntity<Object> getZbBankAuths(ZbBankAuthQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbBankAuthService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增bankAuth")
    @ApiOperation("新增bankAuth")
    @PreAuthorize("@el.check('admin','zbBankAuth:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbBankAuth resources){
        return new ResponseEntity<>(zbBankAuthService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改bankAuth")
    @ApiOperation("修改bankAuth")
    @PreAuthorize("@el.check('admin','zbBankAuth:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbBankAuth resources){
        zbBankAuthService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除bankAuth")
    @ApiOperation("删除bankAuth")
    @PreAuthorize("@el.check('admin','zbBankAuth:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbBankAuthService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/bankAuthList")
    @Log("查询bankAuth")
    @ApiOperation("查询bankAuth")
    //@PreAuthorize("@el.check('admin','zbBankAuth:list')")
    @AnonymousAccess
    public ResponseEntity<Object> bankAuthList(ZbBankAuthQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbBankAuthService.bankAuthList(criteria,pageable),HttpStatus.OK);
    }


    @Log("银行卡认证审核")
    @ApiOperation(value = "银行卡认证审核")
    @PostMapping(value = "/onStatus")
    @AnonymousAccess
    public ResponseEntity onStatus(@RequestParam(name="id") Integer id,@RequestParam(name="status") Integer status){
        zbBankAuthService.onStatus(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }
}
