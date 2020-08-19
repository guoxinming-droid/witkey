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
import co.zhenxi.modules.shop.domain.ZbFeedback;
import co.zhenxi.modules.shop.service.ZbFeedbackService;
import co.zhenxi.modules.shop.service.dto.ZbFeedbackDto;
import co.zhenxi.modules.shop.service.dto.ZbFeedbackQueryCriteria;
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
@Api(tags = "用户反馈管理")
@RestController
@RequestMapping("/api/zbFeedback")
public class ZbFeedbackController {

    private final ZbFeedbackService zbFeedbackService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbFeedback:list')")
    public void download(HttpServletResponse response, ZbFeedbackQueryCriteria criteria) throws IOException {
        zbFeedbackService.download(generator.convert(zbFeedbackService.queryAll(criteria), ZbFeedbackDto.class), response);
    }

    @GetMapping
    @Log("查询feedback")
    @ApiOperation("查询feedback")
    @PreAuthorize("@el.check('admin','zbFeedback:list')")
    public ResponseEntity<Object> getZbFeedbacks(ZbFeedbackQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbFeedbackService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增feedback")
    @ApiOperation("新增feedback")
    @PreAuthorize("@el.check('admin','zbFeedback:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbFeedback resources){
        return new ResponseEntity<>(zbFeedbackService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改feedback")
    @ApiOperation("修改feedback")
    @PreAuthorize("@el.check('admin','zbFeedback:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbFeedback resources){
        zbFeedbackService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除feedback")
    @ApiOperation("删除feedback")
    @PreAuthorize("@el.check('admin','zbFeedback:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbFeedbackService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/feedbackList")
    @Log("查询feedback")
    @ApiOperation("查询feedback")
    //@PreAuthorize("@el.check('admin','zbFeedback:list')")
    @AnonymousAccess
    public ResponseEntity<Object> feedbackList(Integer user,Integer status,String StartTime,String endTime, Pageable pageable){
        return new ResponseEntity<>(zbFeedbackService.feedbackList(user,status,StartTime,endTime,pageable),HttpStatus.OK);
    }
}
