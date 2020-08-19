/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbAgreement;
import co.zhenxi.modules.shop.service.ZbAgreementService;
import co.zhenxi.modules.shop.service.dto.ZbAgreementDto;
import co.zhenxi.modules.shop.service.dto.ZbAgreementQueryCriteria;
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
* @author Gxm
* @date 2020-07-16
*/
@AllArgsConstructor
@Api(tags = "协议管理")
@RestController
@RequestMapping("/api/zbAgreement")
public class ZbAgreementController {

    private final ZbAgreementService zbAgreementService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbAgreement:list')")
    public void download(HttpServletResponse response, ZbAgreementQueryCriteria criteria) throws IOException {
        zbAgreementService.download(generator.convert(zbAgreementService.queryAll(criteria), ZbAgreementDto.class), response);
    }

    @GetMapping
    @Log("查询协议")
    @ApiOperation("查询协议")
    @PreAuthorize("@el.check('admin','zbAgreement:list')")
    public ResponseEntity<Object> getZbAgreements(ZbAgreementQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbAgreementService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增协议")
    @ApiOperation("新增协议")
    @PreAuthorize("@el.check('admin','zbAgreement:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbAgreement resources){
        return new ResponseEntity<>(zbAgreementService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改协议")
    @ApiOperation("修改协议")
    @PreAuthorize("@el.check('admin','zbAgreement:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbAgreement resources){
        zbAgreementService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除协议")
    @ApiOperation("删除协议")
    @PreAuthorize("@el.check('admin','zbAgreement:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbAgreementService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
