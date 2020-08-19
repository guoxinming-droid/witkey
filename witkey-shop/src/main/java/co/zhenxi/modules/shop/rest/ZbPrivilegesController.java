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
import co.zhenxi.modules.shop.domain.ZbPrivileges;
import co.zhenxi.modules.shop.service.ZbPrivilegesService;
import co.zhenxi.modules.shop.service.dto.ZbPrivilegesDto;
import co.zhenxi.modules.shop.service.dto.ZbPrivilegesQueryCriteria;
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
@Api(tags = "特权管理")
@RestController
@RequestMapping("/api/zbPrivileges")
public class ZbPrivilegesController {

    private final ZbPrivilegesService zbPrivilegesService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbPrivileges:list')")
    public void download(HttpServletResponse response, ZbPrivilegesQueryCriteria criteria) throws IOException {
        zbPrivilegesService.download(generator.convert(zbPrivilegesService.queryAll(criteria), ZbPrivilegesDto.class), response);
    }

    @GetMapping
    @Log("查询特权")
    @ApiOperation("查询特权")
    @PreAuthorize("@el.check('admin','zbPrivileges:list')")
    public ResponseEntity<Object> getZbPrivilegess(ZbPrivilegesQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbPrivilegesService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增特权")
    @ApiOperation("新增特权")
    @PreAuthorize("@el.check('admin','zbPrivileges:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbPrivileges resources){
        return new ResponseEntity<>(zbPrivilegesService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改特权")
    @ApiOperation("修改特权")
    @PreAuthorize("@el.check('admin','zbPrivileges:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbPrivileges resources){
        zbPrivilegesService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除特权")
    @ApiOperation("删除特权")
    @PreAuthorize("@el.check('admin','zbPrivileges:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbPrivilegesService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("更改特权启用状态")
    @ApiOperation(value = "更改特权启用状态")
    @PostMapping(value = "/onStatus")
    @AnonymousAccess
    public ResponseEntity onStatus(@RequestParam Integer id,@RequestParam Integer status){
        zbPrivilegesService.onStatus(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }


    @Log("更改特权推荐状态")
    @ApiOperation(value = "更改特权推荐状态")
    @PostMapping(value = "/isRecommend")
    @AnonymousAccess
    public ResponseEntity isRecommend(@RequestParam Integer id,@RequestParam Integer recommend){
        zbPrivilegesService.isRecommend(id,recommend);
        return new ResponseEntity(HttpStatus.OK);
    }


}
