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
import co.zhenxi.modules.shop.domain.ZbFastTask;
import co.zhenxi.modules.shop.service.ZbFastTaskService;
import co.zhenxi.modules.shop.service.dto.ZbFastTaskDto;
import co.zhenxi.modules.shop.service.dto.ZbFastTaskQueryCriteria;
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
* @date 2020-07-21
*/
@AllArgsConstructor
@Api(tags = "一级任务管理")
@RestController
@RequestMapping("/api/zbFastTask")
public class ZbFastTaskController {

    private final ZbFastTaskService zbFastTaskService;
    private final IGenerator generator;




    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    //@PreAuthorize("@el.check('admin','zbFastTask:list')")
    @AnonymousAccess
    public void download(HttpServletResponse response, ZbFastTaskQueryCriteria criteria) throws IOException {
        zbFastTaskService.download(generator.convert(zbFastTaskService.queryAll(criteria), ZbFastTaskDto.class), response);
    }

    @GetMapping
    @Log("查询一级任务")
    @ApiOperation("查询一级任务")
    @PreAuthorize("@el.check('admin','zbFastTask:list')")
    public ResponseEntity<Object> getZbFastTasks(ZbFastTaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbFastTaskService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增一级任务")
    @ApiOperation("新增一级任务")
    @PreAuthorize("@el.check('admin','zbFastTask:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbFastTask resources){
        return new ResponseEntity<>(zbFastTaskService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改一级任务")
    @ApiOperation("修改一级任务")
    @PreAuthorize("@el.check('admin','zbFastTask:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbFastTask resources){
        zbFastTaskService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除一级任务")
    @ApiOperation("删除一级任务")
    @PreAuthorize("@el.check('admin','zbFastTask:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbFastTaskService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
