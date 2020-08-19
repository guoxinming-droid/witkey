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
import co.zhenxi.modules.shop.domain.ZbSkillTags;
import co.zhenxi.modules.shop.service.ZbSkillTagsService;
import co.zhenxi.modules.shop.service.dto.ZbSkillTagsQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbSkillTagsDto;
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
* @date 2020-08-15
*/
@AllArgsConstructor
@Api(tags = "技能标签管理")
@RestController
@RequestMapping("/api/zbSkillTags")
public class ZbSkillTagsController {

    private final ZbSkillTagsService zbSkillTagsService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbSkillTags:list')")
    public void download(HttpServletResponse response, ZbSkillTagsQueryCriteria criteria) throws IOException {
        zbSkillTagsService.download(generator.convert(zbSkillTagsService.queryAll(criteria), ZbSkillTagsDto.class), response);
    }

    @GetMapping
    @Log("查询技能标签")
    @ApiOperation("查询技能标签")
    @PreAuthorize("@el.check('admin','zbSkillTags:list')")
    public ResponseEntity<Object> getZbSkillTagss(ZbSkillTagsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbSkillTagsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增技能标签")
    @ApiOperation("新增技能标签")
    @PreAuthorize("@el.check('admin','zbSkillTags:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbSkillTags resources){
        return new ResponseEntity<>(zbSkillTagsService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改技能标签")
    @ApiOperation("修改技能标签")
    @PreAuthorize("@el.check('admin','zbSkillTags:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbSkillTags resources){
        zbSkillTagsService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除技能标签")
    @ApiOperation("删除技能标签")
    @PreAuthorize("@el.check('admin','zbSkillTags:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbSkillTagsService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
