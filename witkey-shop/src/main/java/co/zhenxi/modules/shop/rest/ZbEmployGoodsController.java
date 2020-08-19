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
import co.zhenxi.modules.shop.domain.ZbEmployGoods;
import co.zhenxi.modules.shop.service.ZbEmployGoodsService;
import co.zhenxi.modules.shop.service.dto.ZbEmployGoodsQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbEmployGoodsDto;
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
* @date 2020-07-28
*/
@AllArgsConstructor
@Api(tags = "雇佣服务关联管理")
@RestController
@RequestMapping("/api/zbEmployGoods")
public class ZbEmployGoodsController {

    private final ZbEmployGoodsService zbEmployGoodsService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbEmployGoods:list')")
    public void download(HttpServletResponse response, ZbEmployGoodsQueryCriteria criteria) throws IOException {
        zbEmployGoodsService.download(generator.convert(zbEmployGoodsService.queryAll(criteria), ZbEmployGoodsDto.class), response);
    }

    @GetMapping
    @Log("查询雇佣服务关联")
    @ApiOperation("查询雇佣服务关联")
    @PreAuthorize("@el.check('admin','zbEmployGoods:list')")
    public ResponseEntity<Object> getZbEmployGoodss(ZbEmployGoodsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbEmployGoodsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增雇佣服务关联")
    @ApiOperation("新增雇佣服务关联")
    @PreAuthorize("@el.check('admin','zbEmployGoods:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbEmployGoods resources){
        return new ResponseEntity<>(zbEmployGoodsService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改雇佣服务关联")
    @ApiOperation("修改雇佣服务关联")
    @PreAuthorize("@el.check('admin','zbEmployGoods:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbEmployGoods resources){
        zbEmployGoodsService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除雇佣服务关联")
    @ApiOperation("删除雇佣服务关联")
    @PreAuthorize("@el.check('admin','zbEmployGoods:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbEmployGoodsService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
