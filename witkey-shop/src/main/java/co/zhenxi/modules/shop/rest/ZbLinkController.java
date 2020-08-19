/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbLink;
import co.zhenxi.modules.shop.service.ZbLinkService;
import co.zhenxi.modules.shop.service.dto.ZbLinkDto;
import co.zhenxi.modules.shop.service.dto.ZbLinkQueryCriteria;
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
@Api(tags = "友情链接管理")
@RestController
@RequestMapping("/api/zbLink")
public class ZbLinkController {

    private final ZbLinkService zbLinkService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbLink:list')")
    public void download(HttpServletResponse response, ZbLinkQueryCriteria criteria) throws IOException {
        zbLinkService.download(generator.convert(zbLinkService.queryAll(criteria), ZbLinkDto.class), response);
    }

    @GetMapping
    @Log("查询友情链接")
    @ApiOperation("查询友情链接")
    @PreAuthorize("@el.check('admin','zbLink:list')")
    public ResponseEntity<Object> getZbLinks(ZbLinkQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbLinkService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增友情链接")
    @ApiOperation("新增友情链接")
    @PreAuthorize("@el.check('admin','zbLink:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbLink resources){
        return new ResponseEntity<>(zbLinkService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改友情链接")
    @ApiOperation("修改友情链接")
    @PreAuthorize("@el.check('admin','zbLink:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbLink resources){
        zbLinkService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除友情链接")
    @ApiOperation("删除友情链接")
    @PreAuthorize("@el.check('admin','zbLink:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbLinkService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
