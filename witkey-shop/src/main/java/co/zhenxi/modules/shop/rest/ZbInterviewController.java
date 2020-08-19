/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbInterview;
import co.zhenxi.modules.shop.service.ZbInterviewService;
import co.zhenxi.modules.shop.service.dto.ZbInterviewDto;
import co.zhenxi.modules.shop.service.dto.ZbInterviewQueryCriteria;
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
* @date 2020-07-27
*/
@AllArgsConstructor
@Api(tags = "访谈管理管理")
@RestController
@RequestMapping("/api/zbInterview")
public class ZbInterviewController {

    private final ZbInterviewService zbInterviewService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbInterview:list')")
    public void download(HttpServletResponse response, ZbInterviewQueryCriteria criteria) throws IOException {
        zbInterviewService.download(generator.convert(zbInterviewService.queryAll(criteria), ZbInterviewDto.class), response);
    }

    @GetMapping
    @Log("查询访谈管理")
    @ApiOperation("查询访谈管理")
    @PreAuthorize("@el.check('admin','zbInterview:list')")
    public ResponseEntity<Object> getZbInterviews(ZbInterviewQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbInterviewService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增访谈管理")
    @ApiOperation("新增访谈管理")
    @PreAuthorize("@el.check('admin','zbInterview:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbInterview resources){
        return new ResponseEntity<>(zbInterviewService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改访谈管理")
    @ApiOperation("修改访谈管理")
    @PreAuthorize("@el.check('admin','zbInterview:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbInterview resources){
        zbInterviewService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除访谈管理")
    @ApiOperation("删除访谈管理")
    @PreAuthorize("@el.check('admin','zbInterview:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbInterviewService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
