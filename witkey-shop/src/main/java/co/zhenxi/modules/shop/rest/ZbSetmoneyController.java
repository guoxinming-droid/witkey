/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbSetmoney;
import co.zhenxi.modules.shop.service.ZbSetmoneyService;
import co.zhenxi.modules.shop.service.dto.ZbSetmoneyDto;
import co.zhenxi.modules.shop.service.dto.ZbSetmoneyQueryCriteria;
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
* @author Guo
* @date 2020-07-20
*/
@AllArgsConstructor
@Api(tags = "交易金额限制管理")
@RestController
@RequestMapping("/api/zbSetmoney")
public class ZbSetmoneyController {

    private final ZbSetmoneyService zbSetmoneyService;
    private final IGenerator generator;




    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbSetmoney:list')")
    public void download(HttpServletResponse response, ZbSetmoneyQueryCriteria criteria) throws IOException {
        zbSetmoneyService.download(generator.convert(zbSetmoneyService.queryAll(criteria), ZbSetmoneyDto.class), response);
    }

    @GetMapping
    @Log("查询交易金额限制")
    @ApiOperation("查询交易金额限制")
    @PreAuthorize("@el.check('admin','zbSetmoney:list')")
    public ResponseEntity<Object> getZbSetmoneys(ZbSetmoneyQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbSetmoneyService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增交易金额限制")
    @ApiOperation("新增交易金额限制")
    @PreAuthorize("@el.check('admin','zbSetmoney:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbSetmoney resources){
        return new ResponseEntity<>(zbSetmoneyService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改交易金额限制")
    @ApiOperation("修改交易金额限制")
    @PreAuthorize("@el.check('admin','zbSetmoney:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbSetmoney resources){
        zbSetmoneyService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除交易金额限制")
    @ApiOperation("删除交易金额限制")
    @PreAuthorize("@el.check('admin','zbSetmoney:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbSetmoneyService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
