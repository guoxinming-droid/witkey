/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbTaskTemplate;
import co.zhenxi.modules.shop.service.ZbTaskTemplateService;
import co.zhenxi.modules.shop.service.dto.ZbTaskTemplateDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskTemplateQueryCriteria;
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
* @author Guoxm
* @date 2020-07-16
*/
@AllArgsConstructor
@Api(tags = "任务模板管理")
@RestController
@RequestMapping("/api/zbTaskTemplate")
public class ZbTaskTemplateController {

    private final ZbTaskTemplateService zbTaskTemplateService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbTaskTemplate:list')")
    public void download(HttpServletResponse response, ZbTaskTemplateQueryCriteria criteria) throws IOException {
        zbTaskTemplateService.download(generator.convert(zbTaskTemplateService.queryAll(criteria), ZbTaskTemplateDto.class), response);
    }

    @GetMapping
    @Log("查询任务模板")
    @ApiOperation("查询任务模板")
    @PreAuthorize("@el.check('admin','zbTaskTemplate:list')")
    public ResponseEntity<Object> getZbTaskTemplates(ZbTaskTemplateQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTaskTemplateService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增任务模板")
    @ApiOperation("新增任务模板")
    @PreAuthorize("@el.check('admin','zbTaskTemplate:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbTaskTemplate resources){
        return new ResponseEntity<>(zbTaskTemplateService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改任务模板")
    @ApiOperation("修改任务模板")
    @PreAuthorize("@el.check('admin','zbTaskTemplate:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbTaskTemplate resources){
        zbTaskTemplateService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除任务模板")
    @ApiOperation("删除任务模板")
    @PreAuthorize("@el.check('admin','zbTaskTemplate:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbTaskTemplateService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
