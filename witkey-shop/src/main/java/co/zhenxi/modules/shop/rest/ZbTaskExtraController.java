/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbTaskExtra;
import co.zhenxi.modules.shop.service.ZbTaskExtraService;
import co.zhenxi.modules.shop.service.dto.ZbTaskExtraDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskExtraQueryCriteria;
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
* @date 2020-07-22
*/
@AllArgsConstructor
@Api(tags = "seo管理")
@RestController
@RequestMapping("/api/zbTaskExtra")
public class ZbTaskExtraController {

    private final ZbTaskExtraService zbTaskExtraService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbTaskExtra:list')")
    public void download(HttpServletResponse response, ZbTaskExtraQueryCriteria criteria) throws IOException {
        zbTaskExtraService.download(generator.convert(zbTaskExtraService.queryAll(criteria), ZbTaskExtraDto.class), response);
    }

    @GetMapping
    @Log("查询seo")
    @ApiOperation("查询seo")
    @PreAuthorize("@el.check('admin','zbTaskExtra:list')")
    public ResponseEntity<Object> getZbTaskExtras(ZbTaskExtraQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTaskExtraService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增seo")
    @ApiOperation("新增seo")
    @PreAuthorize("@el.check('admin','zbTaskExtra:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbTaskExtra resources){
        return new ResponseEntity<>(zbTaskExtraService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改seo")
    @ApiOperation("修改seo")
    @PreAuthorize("@el.check('admin','zbTaskExtra:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbTaskExtra resources){
        zbTaskExtraService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除seo")
    @ApiOperation("删除seo")
    @PreAuthorize("@el.check('admin','zbTaskExtra:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbTaskExtraService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
