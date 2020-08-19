/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbRecommend;
import co.zhenxi.modules.shop.service.ZbRecommendService;
import co.zhenxi.modules.shop.service.dto.ZbRecommendDto;
import co.zhenxi.modules.shop.service.dto.ZbRecommendQueryCriteria;
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
@Api(tags = "推荐管理")
@RestController
@RequestMapping("/api/zbRecommend")
public class ZbRecommendController {

    private final ZbRecommendService zbRecommendService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbRecommend:list')")
    public void download(HttpServletResponse response, ZbRecommendQueryCriteria criteria) throws IOException {
        zbRecommendService.download(generator.convert(zbRecommendService.queryAll(criteria), ZbRecommendDto.class), response);
    }

    @GetMapping
    @Log("查询推荐")
    @ApiOperation("查询推荐")
    @PreAuthorize("@el.check('admin','zbRecommend:list')")
    public ResponseEntity<Object> getZbRecommends(ZbRecommendQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbRecommendService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增推荐")
    @ApiOperation("新增推荐")
    @PreAuthorize("@el.check('admin','zbRecommend:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbRecommend resources){
        return new ResponseEntity<>(zbRecommendService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改推荐")
    @ApiOperation("修改推荐")
    @PreAuthorize("@el.check('admin','zbRecommend:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbRecommend resources){
        zbRecommendService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除推荐")
    @ApiOperation("删除推荐")
    @PreAuthorize("@el.check('admin','zbRecommend:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbRecommendService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
