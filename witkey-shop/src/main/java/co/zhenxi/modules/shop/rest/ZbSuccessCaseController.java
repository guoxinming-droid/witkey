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
import co.zhenxi.modules.shop.domain.ZbSuccessCase;
import co.zhenxi.modules.shop.service.ZbSuccessCaseService;
import co.zhenxi.modules.shop.service.dto.ZbSuccessCaseDto;
import co.zhenxi.modules.shop.service.dto.ZbSuccessCaseQueryCriteria;
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
* @date 2020-07-30
*/
@AllArgsConstructor
@Api(tags = "成功案例管理")
@RestController
@RequestMapping("/api/zbSuccessCase")
public class ZbSuccessCaseController {

    private final ZbSuccessCaseService zbSuccessCaseService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbSuccessCase:list')")
    public void download(HttpServletResponse response, ZbSuccessCaseQueryCriteria criteria) throws IOException {
        zbSuccessCaseService.download(generator.convert(zbSuccessCaseService.queryAll(criteria), ZbSuccessCaseDto.class), response);
    }

    @GetMapping
    @Log("查询成功案例")
    @ApiOperation("查询成功案例")
    @PreAuthorize("@el.check('admin','zbSuccessCase:list')")
    public ResponseEntity<Object> getZbSuccessCases(ZbSuccessCaseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbSuccessCaseService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping("/create")
    @Log("新增成功案例")
    @ApiOperation("新增成功案例")
    //@PreAuthorize("@el.check('admin','zbSuccessCase:add')")
    @AnonymousAccess
    public ResponseEntity<Object> create(@Validated @RequestBody ZbSuccessCase resources){
        return new ResponseEntity<>(zbSuccessCaseService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改成功案例")
    @ApiOperation("修改成功案例")
    @PreAuthorize("@el.check('admin','zbSuccessCase:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbSuccessCase resources){
        zbSuccessCaseService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除成功案例")
    @ApiOperation("删除成功案例")
    @PreAuthorize("@el.check('admin','zbSuccessCase:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbSuccessCaseService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/successCaseList")
    @Log("查询成功案例列表")
    @ApiOperation("查询成功案例列表")
    //@PreAuthorize("@el.check('admin','zbSuccessCase:list')")
    @AnonymousAccess
    public ResponseEntity<Object> successCaseList(ZbSuccessCaseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbSuccessCaseService.successCaseList(criteria,pageable),HttpStatus.OK);
    }


}
