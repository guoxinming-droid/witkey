/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbTaskType;
import co.zhenxi.modules.shop.service.ZbTaskTypeService;
import co.zhenxi.modules.shop.service.dto.ZbTaskTypeDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskTypeQueryCriteria;
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
* @date 2020-07-22
*/
@AllArgsConstructor
@Api(tags = "任务类型管理")
@RestController
@RequestMapping("/api/zbTaskType")
public class ZbTaskTypeController {

    private final ZbTaskTypeService zbTaskTypeService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbTaskType:list')")
    public void download(HttpServletResponse response, ZbTaskTypeQueryCriteria criteria) throws IOException {
        zbTaskTypeService.download(generator.convert(zbTaskTypeService.queryAll(criteria), ZbTaskTypeDto.class), response);
    }

    @GetMapping
    @Log("查询任务类型")
    @ApiOperation("查询任务类型")
    @PreAuthorize("@el.check('admin','zbTaskType:list')")
    public ResponseEntity<Object> getZbTaskTypes(ZbTaskTypeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTaskTypeService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增任务类型")
    @ApiOperation("新增任务类型")
    @PreAuthorize("@el.check('admin','zbTaskType:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbTaskType resources){
        return new ResponseEntity<>(zbTaskTypeService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改任务类型")
    @ApiOperation("修改任务类型")
    @PreAuthorize("@el.check('admin','zbTaskType:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbTaskType resources){
        zbTaskTypeService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除任务类型")
    @ApiOperation("删除任务类型")
    @PreAuthorize("@el.check('admin','zbTaskType:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbTaskTypeService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
