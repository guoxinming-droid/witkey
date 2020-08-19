/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbTaskAttachment;
import co.zhenxi.modules.shop.service.ZbTaskAttachmentService;
import co.zhenxi.modules.shop.service.dto.ZbTaskAttachmentDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskAttachmentQueryCriteria;
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
@Api(tags = "任务附件关联管理")
@RestController
@RequestMapping("/api/zbTaskAttachment")
public class ZbTaskAttachmentController {

    private final ZbTaskAttachmentService zbTaskAttachmentService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbTaskAttachment:list')")
    public void download(HttpServletResponse response, ZbTaskAttachmentQueryCriteria criteria) throws IOException {
        zbTaskAttachmentService.download(generator.convert(zbTaskAttachmentService.queryAll(criteria), ZbTaskAttachmentDto.class), response);
    }

    @GetMapping
    @Log("查询任务附件关联")
    @ApiOperation("查询任务附件关联")
    @PreAuthorize("@el.check('admin','zbTaskAttachment:list')")
    public ResponseEntity<Object> getZbTaskAttachments(ZbTaskAttachmentQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTaskAttachmentService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增任务附件关联")
    @ApiOperation("新增任务附件关联")
    @PreAuthorize("@el.check('admin','zbTaskAttachment:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbTaskAttachment resources){
        return new ResponseEntity<>(zbTaskAttachmentService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改任务附件关联")
    @ApiOperation("修改任务附件关联")
    @PreAuthorize("@el.check('admin','zbTaskAttachment:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbTaskAttachment resources){
        zbTaskAttachmentService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除任务附件关联")
    @ApiOperation("删除任务附件关联")
    @PreAuthorize("@el.check('admin','zbTaskAttachment:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbTaskAttachmentService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
