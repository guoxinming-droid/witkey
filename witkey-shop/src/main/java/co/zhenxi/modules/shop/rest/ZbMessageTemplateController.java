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
import co.zhenxi.modules.shop.domain.ZbMessageTemplate;
import co.zhenxi.modules.shop.service.ZbMessageTemplateService;
import co.zhenxi.modules.shop.service.dto.ZbMessageTemplateDto;
import co.zhenxi.modules.shop.service.dto.ZbMessageTemplateQueryCriteria;
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
* @date 2020-08-05
*/
@AllArgsConstructor
@Api(tags = "消息模板管理")
@RestController
@RequestMapping("/api/zbMessageTemplate")
public class ZbMessageTemplateController {

    private final ZbMessageTemplateService zbMessageTemplateService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbMessageTemplate:list')")
    public void download(HttpServletResponse response, ZbMessageTemplateQueryCriteria criteria) throws IOException {
        zbMessageTemplateService.download(generator.convert(zbMessageTemplateService.queryAll(criteria), ZbMessageTemplateDto.class), response);
    }

    @GetMapping
    @Log("查询消息模板")
    @ApiOperation("查询消息模板")
    @PreAuthorize("@el.check('admin','zbMessageTemplate:list')")
    public ResponseEntity<Object> getZbMessageTemplates(ZbMessageTemplateQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbMessageTemplateService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增消息模板")
    @ApiOperation("新增消息模板")
    @PreAuthorize("@el.check('admin','zbMessageTemplate:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbMessageTemplate resources){
        return new ResponseEntity<>(zbMessageTemplateService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改消息模板")
    @ApiOperation("修改消息模板")
    @PreAuthorize("@el.check('admin','zbMessageTemplate:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbMessageTemplate resources){
        zbMessageTemplateService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除消息模板")
    @ApiOperation("删除消息模板")
    @PreAuthorize("@el.check('admin','zbMessageTemplate:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbMessageTemplateService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/messageList")
    @Log("查询消息模板")
    @ApiOperation("查询消息模板")
    //@PreAuthorize("@el.check('admin','zbMessageTemplate:list')")
    @AnonymousAccess
    public ResponseEntity<Object> messageList(ZbMessageTemplateQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbMessageTemplateService.queryAll(criteria,pageable),HttpStatus.OK);
    }



    @Log("消息模板状态变更")
    @ApiOperation(value = "消息模板状态变更")
    @PostMapping(value = "/changeStatus")
    @AnonymousAccess
    public Boolean changeStatus(@RequestParam(name="id") Integer id,@RequestParam(name="isName") Integer isName,@RequestParam(name="status") Integer status){

        return  zbMessageTemplateService.changeStatus(id,isName,status);
    }


}
