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
import co.zhenxi.modules.shop.domain.ZbTask;
import co.zhenxi.modules.shop.domain.ZbWorkComments;
import co.zhenxi.modules.shop.service.ZbTaskService;
import co.zhenxi.modules.shop.service.ZbWorkCommentsService;
import co.zhenxi.modules.shop.service.dto.ZbTaskDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskQueryCriteria;
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
import java.util.List;

/**
* @author Guoxm
* @date 2020-07-16
*/
@AllArgsConstructor
@Api(tags = "任务管理")
@RestController
@RequestMapping("/api/zbTask")
public class ZbTaskController {

    private final ZbTaskService zbTaskService;
    private final IGenerator generator;
    private final ZbWorkCommentsService zbWorkCommentsService;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbTask:list')")
    public void download(HttpServletResponse response, ZbTaskQueryCriteria criteria) throws IOException {
        zbTaskService.download(generator.convert(zbTaskService.queryXSAll(criteria), ZbTaskDto.class), response);
    }

    @GetMapping(value = "/xsList")
    @Log("查询悬赏任务")
    @ApiOperation("查询悬赏任务")
 //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getXSZbTasks(ZbTaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTaskService.queryXSAll(criteria,pageable),HttpStatus.OK);
    }


    @GetMapping(value = "/zbList")
    @Log("查询招标任务")
    @ApiOperation("查询招标任务")
    //   @PreAuthorize("@el.check('admin','ZBZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getZBZbTasks(ZbTaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTaskService.queryZBAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增任务")
    @ApiOperation("新增任务")
    @PreAuthorize("@el.check('admin','zbTask:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbTask resources){
        return new ResponseEntity<>(zbTaskService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改任务")
    @ApiOperation("修改任务")
    @PreAuthorize("@el.check('admin','zbTask:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbTask resources){
        zbTaskService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除任务")
    @ApiOperation("删除任务")
    @PreAuthorize("@el.check('admin','zbTask:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbTaskService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping(value = "/getTasksById")
    @Log("查询任务详情")
    @ApiOperation("查询任务详情")
    //   @PreAuthorize("@el.check('admin','ZBZbTasks:list')")
    @AnonymousAccess
    public ZbTask getTasksById(@RequestParam long id){
        return zbTaskService.getTasksById(id);
    }


    @GetMapping(value = "/getTasksWorkById")
    @Log("查询任务稿件")
    @ApiOperation("查询任务稿件")
    //   @PreAuthorize("@el.check('admin','ZBZbTasks:list')")
    @AnonymousAccess
    public ZbTask getTasksWorkById(@RequestParam long id){
        return zbTaskService.getTasksWorkById(id);
    }


    @GetMapping(value = "/getWorkCommentsById")
    @Log("查询任务留言")
    @ApiOperation("查询任务留言")
    //   @PreAuthorize("@el.check('admin','ZBZbTasks:list')")
    @AnonymousAccess
    public List<ZbWorkComments> getWorkCommentsById(@RequestParam long id){
        return zbWorkCommentsService.getWorkCommentsById(id);
    }





}
