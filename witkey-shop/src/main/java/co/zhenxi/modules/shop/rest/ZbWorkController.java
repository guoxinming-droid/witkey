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
import co.zhenxi.modules.shop.domain.ZbWork;
import co.zhenxi.modules.shop.service.ZbWorkService;
import co.zhenxi.modules.shop.service.dto.ZbWorkDto;
import co.zhenxi.modules.shop.service.dto.ZbWorkQueryCriteria;
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
* @date 2020-07-23
*/
@AllArgsConstructor
@Api(tags = "稿件管理")
@RestController
@RequestMapping("/api/zbWork")
public class ZbWorkController {

    private final ZbWorkService zbWorkService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbWork:list')")
    public void download(HttpServletResponse response, ZbWorkQueryCriteria criteria) throws IOException {
        zbWorkService.download(generator.convert(zbWorkService.queryAll(criteria), ZbWorkDto.class), response);
    }

    @GetMapping
    @Log("查询稿件")
    @ApiOperation("查询稿件")
    @PreAuthorize("@el.check('admin','zbWork:list')")
    public ResponseEntity<Object> getZbWorks(ZbWorkQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbWorkService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增稿件")
    @ApiOperation("新增稿件")
    @PreAuthorize("@el.check('admin','zbWork:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbWork resources){
        return new ResponseEntity<>(zbWorkService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改稿件")
    @ApiOperation("修改稿件")
    @PreAuthorize("@el.check('admin','zbWork:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbWork resources){
        zbWorkService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除稿件")
    @ApiOperation("删除稿件")
    @PreAuthorize("@el.check('admin','zbWork:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbWorkService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getWorkByTaskId")
    @Log("查询任务投稿记录列表")
    @ApiOperation("查询任务投稿记录列表")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getWorkByTaskId(Integer taskId){
        return new ResponseEntity<>(zbWorkService.getWorkByTaskId(taskId), HttpStatus.OK);
    }

}
