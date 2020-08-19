/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbRecommendPosition;
import co.zhenxi.modules.shop.service.ZbRecommendPositionService;
import co.zhenxi.modules.shop.service.dto.ZbRecommendPositionDto;
import co.zhenxi.modules.shop.service.dto.ZbRecommendPositionQueryCriteria;
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
@Api(tags = "推荐位管理")
@RestController
@RequestMapping("/api/zbRecommendPosition")
public class ZbRecommendPositionController {

    private final ZbRecommendPositionService zbRecommendPositionService;
    private final IGenerator generator;



    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbRecommendPosition:list')")
    public void download(HttpServletResponse response, ZbRecommendPositionQueryCriteria criteria) throws IOException {
        zbRecommendPositionService.download(generator.convert(zbRecommendPositionService.queryAll(criteria), ZbRecommendPositionDto.class), response);
    }

    @GetMapping
    @Log("查询推荐位")
    @ApiOperation("查询推荐位")
    @PreAuthorize("@el.check('admin','zbRecommendPosition:list')")
    public ResponseEntity<Object> getZbRecommendPositions(ZbRecommendPositionQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbRecommendPositionService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增推荐位")
    @ApiOperation("新增推荐位")
    @PreAuthorize("@el.check('admin','zbRecommendPosition:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbRecommendPosition resources){
        return new ResponseEntity<>(zbRecommendPositionService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改推荐位")
    @ApiOperation("修改推荐位")
    @PreAuthorize("@el.check('admin','zbRecommendPosition:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbRecommendPosition resources){
        zbRecommendPositionService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除推荐位")
    @ApiOperation("删除推荐位")
    @PreAuthorize("@el.check('admin','zbRecommendPosition:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbRecommendPositionService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
