/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbTaskReport;
import co.zhenxi.modules.shop.service.ZbTaskReportService;
import co.zhenxi.modules.shop.service.dto.ZbTaskReportDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskReportQueryCriteria;
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
@Api(tags = "举报管理")
@RestController
@RequestMapping("/api/zbTaskReport")
public class ZbTaskReportController {

    private final ZbTaskReportService zbTaskReportService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbTaskReport:list')")
    public void download(HttpServletResponse response, ZbTaskReportQueryCriteria criteria) throws IOException {
        zbTaskReportService.download(generator.convert(zbTaskReportService.queryAll(criteria), ZbTaskReportDto.class), response);
    }

    @GetMapping
    @Log("查询举报")
    @ApiOperation("查询举报")
    @PreAuthorize("@el.check('admin','zbTaskReport:list')")
    public ResponseEntity<Object> getZbTaskReports(ZbTaskReportQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTaskReportService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增举报")
    @ApiOperation("新增举报")
    @PreAuthorize("@el.check('admin','zbTaskReport:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbTaskReport resources){
        return new ResponseEntity<>(zbTaskReportService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改举报")
    @ApiOperation("修改举报")
    @PreAuthorize("@el.check('admin','zbTaskReport:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbTaskReport resources){
        zbTaskReportService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除举报")
    @ApiOperation("删除举报")
    @PreAuthorize("@el.check('admin','zbTaskReport:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbTaskReportService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
