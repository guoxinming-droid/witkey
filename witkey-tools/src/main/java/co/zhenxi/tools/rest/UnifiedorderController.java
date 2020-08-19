/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.rest;
import java.util.Arrays;
import co.zhenxi.dozer.service.IGenerator;
import lombok.AllArgsConstructor;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.tools.domain.Unifiedorder;
import co.zhenxi.tools.service.UnifiedorderService;
import co.zhenxi.tools.service.dto.UnifiedorderQueryCriteria;
import co.zhenxi.tools.service.dto.UnifiedorderDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Guoxm
* @date 2020-07-18
*/
@AllArgsConstructor
@Api(tags = "微信统一服务管理")
@RestController
@RequestMapping("/api/unifiedorder")
public class UnifiedorderController {

    private final UnifiedorderService unifiedorderService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','unifiedorder:list')")
    public void download(HttpServletResponse response, UnifiedorderQueryCriteria criteria) throws IOException {
        unifiedorderService.download(generator.convert(unifiedorderService.queryAll(criteria), UnifiedorderDto.class), response);
    }

    @GetMapping
    @Log("查询微信统一服务")
    @ApiOperation("查询微信统一服务")
    @PreAuthorize("@el.check('admin','unifiedorder:list')")
    public ResponseEntity<Object> getUnifiedorders(UnifiedorderQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(unifiedorderService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增微信统一服务")
    @ApiOperation("新增微信统一服务")
    @PreAuthorize("@el.check('admin','unifiedorder:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Unifiedorder resources){
        return new ResponseEntity<>(unifiedorderService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改微信统一服务")
    @ApiOperation("修改微信统一服务")
    @PreAuthorize("@el.check('admin','unifiedorder:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Unifiedorder resources){
        unifiedorderService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除微信统一服务")
    @ApiOperation("删除微信统一服务")
    @PreAuthorize("@el.check('admin','unifiedorder:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            unifiedorderService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
