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
import co.zhenxi.modules.shop.domain.ZbSubOrder;
import co.zhenxi.modules.shop.service.ZbSubOrderService;
import co.zhenxi.modules.shop.service.dto.ZbSubOrderQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbSubOrderDto;
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
* @date 2020-08-01
*/
@AllArgsConstructor
@Api(tags = "子订单管理")
@RestController
@RequestMapping("/api/zbSubOrder")
public class ZbSubOrderController {

    private final ZbSubOrderService zbSubOrderService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbSubOrder:list')")
    public void download(HttpServletResponse response, ZbSubOrderQueryCriteria criteria) throws IOException {
        zbSubOrderService.download(generator.convert(zbSubOrderService.queryAll(criteria), ZbSubOrderDto.class), response);
    }

    @GetMapping
    @Log("查询子订单")
    @ApiOperation("查询子订单")
    @PreAuthorize("@el.check('admin','zbSubOrder:list')")
    public ResponseEntity<Object> getZbSubOrders(ZbSubOrderQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbSubOrderService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增子订单")
    @ApiOperation("新增子订单")
    @PreAuthorize("@el.check('admin','zbSubOrder:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbSubOrder resources){
        return new ResponseEntity<>(zbSubOrderService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改子订单")
    @ApiOperation("修改子订单")
    @PreAuthorize("@el.check('admin','zbSubOrder:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbSubOrder resources){
        zbSubOrderService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除子订单")
    @ApiOperation("删除子订单")
    @PreAuthorize("@el.check('admin','zbSubOrder:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbSubOrderService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
