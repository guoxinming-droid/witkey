/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbWorkComments;
import co.zhenxi.modules.shop.service.ZbWorkCommentsService;
import co.zhenxi.modules.shop.service.dto.ZbWorkCommentsDto;
import co.zhenxi.modules.shop.service.dto.ZbWorkCommentsQueryCriteria;
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
* @date 2020-07-23
*/
@AllArgsConstructor
@Api(tags = "稿件评论管理")
@RestController
@RequestMapping("/api/zbWorkComments")
public class ZbWorkCommentsController {

    private final ZbWorkCommentsService zbWorkCommentsService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbWorkComments:list')")
    public void download(HttpServletResponse response, ZbWorkCommentsQueryCriteria criteria) throws IOException {
        zbWorkCommentsService.download(generator.convert(zbWorkCommentsService.queryAll(criteria), ZbWorkCommentsDto.class), response);
    }

    @GetMapping
    @Log("查询稿件评论")
    @ApiOperation("查询稿件评论")
    @PreAuthorize("@el.check('admin','zbWorkComments:list')")
    public ResponseEntity<Object> getZbWorkCommentss(ZbWorkCommentsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbWorkCommentsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增稿件评论")
    @ApiOperation("新增稿件评论")
    @PreAuthorize("@el.check('admin','zbWorkComments:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbWorkComments resources){
        return new ResponseEntity<>(zbWorkCommentsService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改稿件评论")
    @ApiOperation("修改稿件评论")
    @PreAuthorize("@el.check('admin','zbWorkComments:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbWorkComments resources){
        zbWorkCommentsService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除稿件评论")
    @ApiOperation("删除稿件评论")
    @PreAuthorize("@el.check('admin','zbWorkComments:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbWorkCommentsService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
