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
import co.zhenxi.modules.shop.domain.ZbPromoteType;
import co.zhenxi.modules.shop.service.ZbPromoteTypeService;
import co.zhenxi.modules.shop.service.dto.ZbPromoteTypeDto;
import co.zhenxi.modules.shop.service.dto.ZbPromoteTypeQueryCriteria;
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
* @date 2020-08-06
*/
@AllArgsConstructor
@Api(tags = "推广类型管理")
@RestController
@RequestMapping("/api/zbPromoteType")
public class ZbPromoteTypeController {

    private final ZbPromoteTypeService zbPromoteTypeService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbPromoteType:list')")
    public void download(HttpServletResponse response, ZbPromoteTypeQueryCriteria criteria) throws IOException {
        zbPromoteTypeService.download(generator.convert(zbPromoteTypeService.queryAll(criteria), ZbPromoteTypeDto.class), response);
    }

    @GetMapping
    @Log("查询推广类型")
    @ApiOperation("查询推广类型")
    @PreAuthorize("@el.check('admin','zbPromoteType:list')")
    public ResponseEntity<Object> getZbPromoteTypes(ZbPromoteTypeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbPromoteTypeService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增推广类型")
    @ApiOperation("新增推广类型")
    @PreAuthorize("@el.check('admin','zbPromoteType:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbPromoteType resources){
        return new ResponseEntity<>(zbPromoteTypeService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改推广类型")
    @ApiOperation("修改推广类型")
    @PreAuthorize("@el.check('admin','zbPromoteType:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbPromoteType resources){
        zbPromoteTypeService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除推广类型")
    @ApiOperation("删除推广类型")
    @PreAuthorize("@el.check('admin','zbPromoteType:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbPromoteTypeService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/promoteConfig")
    @Log("查询推广配置")
    @ApiOperation("查询推广配置")
    //  @PreAuthorize("@el.check('admin','zbConfig:list')")
    @AnonymousAccess
    @ResponseBody
    public ZbPromoteType promoteConfig(){
        String  codeName ="ZHUCETUIGUANG";
        return zbPromoteTypeService.promoteConfig(codeName);
    }



    @PutMapping("/postPromoteConfig")
    @Log("修改推广配置")
    @ApiOperation("修改推广配置")
    @AnonymousAccess
    public ResponseEntity<Object> postPromoteConfig(@Validated @RequestBody ZbPromoteType zbPromoteType){
            zbPromoteTypeService.updateById(zbPromoteType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
