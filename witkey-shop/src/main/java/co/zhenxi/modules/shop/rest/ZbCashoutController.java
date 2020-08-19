/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbCashout;
import co.zhenxi.modules.shop.service.ZbCashoutService;
import co.zhenxi.modules.shop.service.dto.ZbCashoutDto;
import co.zhenxi.modules.shop.service.dto.ZbCashoutQueryCriteria;
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
* @date 2020-08-01
*/
@AllArgsConstructor
@Api(tags = "提现管理")
@RestController
@RequestMapping("/api/zbCashout")
public class ZbCashoutController {

    private final ZbCashoutService zbCashoutService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbCashout:list')")
    public void download(HttpServletResponse response, ZbCashoutQueryCriteria criteria) throws IOException {
        zbCashoutService.download(generator.convert(zbCashoutService.queryAll(criteria), ZbCashoutDto.class), response);
    }

    @GetMapping
    @Log("查询提现")
    @ApiOperation("查询提现")
    @PreAuthorize("@el.check('admin','zbCashout:list')")
    public ResponseEntity<Object> getZbCashouts(ZbCashoutQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbCashoutService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增提现")
    @ApiOperation("新增提现")
    @PreAuthorize("@el.check('admin','zbCashout:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbCashout resources){
        return new ResponseEntity<>(zbCashoutService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改提现")
    @ApiOperation("修改提现")
    @PreAuthorize("@el.check('admin','zbCashout:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbCashout resources){
        zbCashoutService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除提现")
    @ApiOperation("删除提现")
    @PreAuthorize("@el.check('admin','zbCashout:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbCashoutService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
