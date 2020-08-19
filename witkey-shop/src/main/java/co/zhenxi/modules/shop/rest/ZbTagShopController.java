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
import co.zhenxi.modules.shop.domain.ZbTagShop;
import co.zhenxi.modules.shop.service.ZbTagShopService;
import co.zhenxi.modules.shop.service.dto.ZbTagShopQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbTagShopDto;
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
@Api(tags = "商城标签关联管理")
@RestController
@RequestMapping("/api/zbTagShop")
public class ZbTagShopController {

    private final ZbTagShopService zbTagShopService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbTagShop:list')")
    public void download(HttpServletResponse response, ZbTagShopQueryCriteria criteria) throws IOException {
        zbTagShopService.download(generator.convert(zbTagShopService.queryAll(criteria), ZbTagShopDto.class), response);
    }

    @GetMapping
    @Log("查询商城标签关联")
    @ApiOperation("查询商城标签关联")
    @PreAuthorize("@el.check('admin','zbTagShop:list')")
    public ResponseEntity<Object> getZbTagShops(ZbTagShopQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTagShopService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增商城标签关联")
    @ApiOperation("新增商城标签关联")
    @PreAuthorize("@el.check('admin','zbTagShop:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbTagShop resources){
        return new ResponseEntity<>(zbTagShopService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改商城标签关联")
    @ApiOperation("修改商城标签关联")
    @PreAuthorize("@el.check('admin','zbTagShop:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbTagShop resources){
        zbTagShopService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除商城标签关联")
    @ApiOperation("删除商城标签关联")
    @PreAuthorize("@el.check('admin','zbTagShop:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbTagShopService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
