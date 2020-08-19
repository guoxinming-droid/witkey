/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import java.util.Arrays;
import co.zhenxi.dozer.service.IGenerator;
import lombok.AllArgsConstructor;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbUserDetail;
import co.zhenxi.modules.shop.service.ZbUserDetailService;
import co.zhenxi.modules.shop.service.dto.ZbUserDetailQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbUserDetailDto;
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
* @author guoke
* @date 2020-07-27
*/
@AllArgsConstructor
@Api(tags = "用户明细管理")
@RestController
@RequestMapping("/api/zbUserDetail")
public class ZbUserDetailController {

    private final ZbUserDetailService zbUserDetailService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbUserDetail:list')")
    public void download(HttpServletResponse response, ZbUserDetailQueryCriteria criteria) throws IOException {
        zbUserDetailService.download(generator.convert(zbUserDetailService.queryAll(criteria), ZbUserDetailDto.class), response);
    }

    @GetMapping
    @Log("查询用户明细")
    @ApiOperation("查询用户明细")
    @PreAuthorize("@el.check('admin','zbUserDetail:list')")
    public ResponseEntity<Object> getZbUserDetails(ZbUserDetailQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbUserDetailService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增用户明细")
    @ApiOperation("新增用户明细")
    @PreAuthorize("@el.check('admin','zbUserDetail:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbUserDetail resources){
        return new ResponseEntity<>(zbUserDetailService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改用户明细")
    @ApiOperation("修改用户明细")
    @PreAuthorize("@el.check('admin','zbUserDetail:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbUserDetail resources){
        zbUserDetailService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除用户明细")
    @ApiOperation("删除用户明细")
    @PreAuthorize("@el.check('admin','zbUserDetail:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbUserDetailService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
