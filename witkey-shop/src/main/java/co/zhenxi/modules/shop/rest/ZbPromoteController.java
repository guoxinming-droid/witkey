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
import co.zhenxi.modules.shop.domain.ZbPromote;
import co.zhenxi.modules.shop.service.ZbPromoteService;
import co.zhenxi.modules.shop.service.dto.ZbPromoteDto;
import co.zhenxi.modules.shop.service.dto.ZbPromoteQueryCriteria;
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
* @date 2020-08-06
*/
@AllArgsConstructor
@Api(tags = "推广管理")
@RestController
@RequestMapping("/api/zbPromote")
public class ZbPromoteController {

    private final ZbPromoteService zbPromoteService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbPromote:list')")
    public void download(HttpServletResponse response, ZbPromoteQueryCriteria criteria) throws IOException {
        zbPromoteService.download(generator.convert(zbPromoteService.queryAll(criteria), ZbPromoteDto.class), response);
    }

    @GetMapping
    @Log("查询推广")
    @ApiOperation("查询推广")
    @PreAuthorize("@el.check('admin','zbPromote:list')")
    public ResponseEntity<Object> getZbPromotes(ZbPromoteQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbPromoteService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增推广")
    @ApiOperation("新增推广")
    @PreAuthorize("@el.check('admin','zbPromote:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbPromote resources){
        return new ResponseEntity<>(zbPromoteService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改推广")
    @ApiOperation("修改推广")
    @PreAuthorize("@el.check('admin','zbPromote:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbPromote resources){
        zbPromoteService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除推广")
    @ApiOperation("删除推广")
    @PreAuthorize("@el.check('admin','zbPromote:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbPromoteService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/promoteRelation")
    @Log("查询推广")
    @ApiOperation("查询推广")
    //@PreAuthorize("@el.check('admin','zbPromote:list')")
    @AnonymousAccess
    public ResponseEntity<Object> promoteRelation(String toName,
                                                  String fromName,
                                                  String startTime,
                                                  String endTime,
                                                  Pageable pageable){
        return new ResponseEntity<>(zbPromoteService.promoteRelation(toName,fromName,startTime,endTime,pageable),HttpStatus.OK);
    }


    @GetMapping("/promoteFinance")
    @Log("查询推广财务")
    @ApiOperation("查询推广财务")
    //@PreAuthorize("@el.check('admin','zbPromote:list')")
    @AnonymousAccess
    public ResponseEntity<Object> promoteFinance(String toName,
                                                  String fromName,
                                                  String startTime,
                                                  String endTime,
                                                  Pageable pageable){
        return new ResponseEntity<>(zbPromoteService.promoteRelation(toName,fromName,startTime,endTime,pageable),HttpStatus.OK);
    }
}
