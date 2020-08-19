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
import co.zhenxi.modules.shop.domain.ZbRealnameAuth;
import co.zhenxi.modules.shop.service.ZbRealnameAuthService;
import co.zhenxi.modules.shop.service.dto.ZbRealnameAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbRealnameAuthQueryCriteria;
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
@Api(tags = "实名认证管理")
@RestController
@RequestMapping("/api/zbRealnameAuth")
public class ZbRealnameAuthController {

    private final ZbRealnameAuthService zbRealnameAuthService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbRealnameAuth:list')")
    public void download(HttpServletResponse response, ZbRealnameAuthQueryCriteria criteria) throws IOException {
        zbRealnameAuthService.download(generator.convert(zbRealnameAuthService.queryAll(criteria), ZbRealnameAuthDto.class), response);
    }

    @GetMapping
    @Log("查询实名认证")
    @ApiOperation("查询实名认证")
    @PreAuthorize("@el.check('admin','zbRealnameAuth:list')")
    public ResponseEntity<Object> getZbRealnameAuths(ZbRealnameAuthQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbRealnameAuthService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增实名认证")
    @ApiOperation("新增实名认证")
    @PreAuthorize("@el.check('admin','zbRealnameAuth:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbRealnameAuth resources){
        return new ResponseEntity<>(zbRealnameAuthService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改实名认证")
    @ApiOperation("修改实名认证")
    @PreAuthorize("@el.check('admin','zbRealnameAuth:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbRealnameAuth resources){
        zbRealnameAuthService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除实名认证")
    @ApiOperation("删除实名认证")
    @PreAuthorize("@el.check('admin','zbRealnameAuth:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbRealnameAuthService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/realnameAuthList")
    @Log("查询实名认证")
    @ApiOperation("查询实名认证")
    //@PreAuthorize("@el.check('admin','zbRealnameAuth:list')")
    @AnonymousAccess
    public ResponseEntity<Object> realnameAuthList(ZbRealnameAuthQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbRealnameAuthService.realnameAuthList(criteria,pageable),HttpStatus.OK);
    }



    @Log("实名认证审核")
    @ApiOperation(value = "实名认证审核")
    @PostMapping(value = "/onStatus")
    @AnonymousAccess
    public ResponseEntity onStatus(@RequestParam Integer id,@RequestParam Integer status){
        zbRealnameAuthService.onStatus(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }

}
