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
import co.zhenxi.modules.shop.domain.ZbShopOrder;
import co.zhenxi.modules.shop.service.ZbShopOrderService;
import co.zhenxi.modules.shop.service.dto.ZbShopOrderDto;
import co.zhenxi.modules.shop.service.dto.ZbShopOrderQueryCriteria;
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
* @date 2020-07-30
*/
@AllArgsConstructor
@Api(tags = "商铺订单管理")
@RestController
@RequestMapping("/api/zbShopOrder")
public class ZbShopOrderController {

    private final ZbShopOrderService zbShopOrderService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbShopOrder:list')")
    public void download(HttpServletResponse response, ZbShopOrderQueryCriteria criteria) throws IOException {
        zbShopOrderService.download(generator.convert(zbShopOrderService.queryAll(criteria), ZbShopOrderDto.class), response);
    }

    @GetMapping
    @Log("查询商铺订单")
    @ApiOperation("查询商铺订单")
    @PreAuthorize("@el.check('admin','zbShopOrder:list')")
    public ResponseEntity<Object> getZbShopOrders(ZbShopOrderQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbShopOrderService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增商铺订单")
    @ApiOperation("新增商铺订单")
    @PreAuthorize("@el.check('admin','zbShopOrder:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbShopOrder resources){
        return new ResponseEntity<>(zbShopOrderService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改商铺订单")
    @ApiOperation("修改商铺订单")
    @PreAuthorize("@el.check('admin','zbShopOrder:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbShopOrder resources){
        zbShopOrderService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除商铺订单")
    @ApiOperation("删除商铺订单")
    @PreAuthorize("@el.check('admin','zbShopOrder:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbShopOrderService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/shopOrderList")
    @Log("查询作品订单")
    @ApiOperation("查询作品订单")
    //@PreAuthorize("@el.check('admin','zbShopOrder:list')")
    @AnonymousAccess
    public ResponseEntity<Object> shopOrderList(ZbShopOrderQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbShopOrderService.shopOrderList(criteria,pageable),HttpStatus.OK);
    }


    @GetMapping("/shopOrderInfo")
    @Log("查询作品详情")
    @ApiOperation("查询作品详情")
    //@PreAuthorize("@el.check('admin','zbShopOrder:list')")
    @AnonymousAccess
    public ResponseEntity<Object> shopOrderInfo(@RequestParam("id") Integer id){
        return new ResponseEntity<>(zbShopOrderService.findById(id),HttpStatus.OK);
    }

}
