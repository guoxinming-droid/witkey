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
import co.zhenxi.modules.shop.domain.ZbConfig;
import co.zhenxi.modules.shop.domain.ZbVipshopOrder;
import co.zhenxi.modules.shop.service.ZbConfigService;
import co.zhenxi.modules.shop.service.ZbVipshopOrderService;
import co.zhenxi.modules.shop.service.dto.ZbVipshopOrderDto;
import co.zhenxi.modules.shop.service.dto.ZbVipshopOrderQueryCriteria;
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
import java.util.List;

/**
* @author guoke
* @date 2020-07-27
*/
@AllArgsConstructor
@Api(tags = "vip店铺订单管理")
@RestController
@RequestMapping("/api/zbVipshopOrder")
public class ZbVipshopOrderController {

    private final ZbVipshopOrderService zbVipshopOrderService;
    private final IGenerator generator;
    private final ZbConfigService zbConfigService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbVipshopOrder:list')")
    public void download(HttpServletResponse response, ZbVipshopOrderQueryCriteria criteria) throws IOException {
        zbVipshopOrderService.download(generator.convert(zbVipshopOrderService.queryAll(criteria), ZbVipshopOrderDto.class), response);
    }

    @GetMapping
    @Log("查询vip店铺订单")
    @ApiOperation("查询vip店铺订单")
    @PreAuthorize("@el.check('admin','zbVipshopOrder:list')")
    public ResponseEntity<Object> getZbVipshopOrders(ZbVipshopOrderQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbVipshopOrderService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增vip店铺订单")
    @ApiOperation("新增vip店铺订单")
    @PreAuthorize("@el.check('admin','zbVipshopOrder:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbVipshopOrder resources){
        return new ResponseEntity<>(zbVipshopOrderService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改vip店铺订单")
    @ApiOperation("修改vip店铺订单")
    @PreAuthorize("@el.check('admin','zbVipshopOrder:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbVipshopOrder resources){
        zbVipshopOrderService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除vip店铺订单")
    @ApiOperation("删除vip店铺订单")
    @PreAuthorize("@el.check('admin','zbVipshopOrder:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbVipshopOrderService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/getVipLogo")
    @Log("查询vip首页LOG配置")
    @ApiOperation("查询vip首页LOG配置")
    //  @PreAuthorize("@el.check('admin','zbConfig:list')")
    @AnonymousAccess
    @ResponseBody
    public List<ZbConfig> getVipLogo(@RequestParam String type){
        return zbConfigService.getZbSiteBy(type);
    }



    @PutMapping("/updateVipLogo")
    @Log("修改vip首页LOG配置")
    @ApiOperation("修改vip首页LOG配置")
    @AnonymousAccess
    public ResponseEntity<Object> updateVipLogo(@Validated  List<ZbConfig> zbConfig){
        for(ZbConfig  Config:zbConfig) {
            zbConfigService.updateById(Config);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
