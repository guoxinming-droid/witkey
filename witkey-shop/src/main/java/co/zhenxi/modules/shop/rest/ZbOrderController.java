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
import co.zhenxi.modules.shop.domain.ZbOrder;
import co.zhenxi.modules.shop.service.ZbOrderService;
import co.zhenxi.modules.shop.service.dto.ZbOrderQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbOrderDto;
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
@Api(tags = "订单管理")
@RestController
@RequestMapping("/api/zbOrder")
public class ZbOrderController {

    private final ZbOrderService zbOrderService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbOrder:list')")
    public void download(HttpServletResponse response, ZbOrderQueryCriteria criteria) throws IOException {
        zbOrderService.download(generator.convert(zbOrderService.queryAll(criteria), ZbOrderDto.class), response);
    }

    @GetMapping
    @Log("查询订单")
    @ApiOperation("查询订单")
    @PreAuthorize("@el.check('admin','zbOrder:list')")
    public ResponseEntity<Object> getZbOrders(ZbOrderQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbOrderService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增订单")
    @ApiOperation("新增订单")
    @PreAuthorize("@el.check('admin','zbOrder:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbOrder resources){
        return new ResponseEntity<>(zbOrderService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改订单")
    @ApiOperation("修改订单")
    @PreAuthorize("@el.check('admin','zbOrder:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbOrder resources){
        zbOrderService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除订单")
    @ApiOperation("删除订单")
    @PreAuthorize("@el.check('admin','zbOrder:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbOrderService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
