/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.tools.domain.WxpayConfig;
import co.zhenxi.tools.service.WxpayConfigService;
import co.zhenxi.tools.service.dto.WxpayConfigDto;
import co.zhenxi.tools.service.dto.WxpayConfigQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
* @author Guo
* @date 2020-07-20
*/
@AllArgsConstructor
@Api(tags = "微信支付配置管理")
@RestController
@RequestMapping("/api/wxpayConfig")
public class WxpayConfigController {

    private final WxpayConfigService wxpayConfigService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','wxpayConfig:list')")
    public void download(HttpServletResponse response, WxpayConfigQueryCriteria criteria) throws IOException {
        wxpayConfigService.download(generator.convert(wxpayConfigService.queryAll(criteria), WxpayConfigDto.class), response);
    }

    @GetMapping
    @Log("查询微信支付配置")
    @ApiOperation("查询微信支付配置")
    @PreAuthorize("@el.check('admin','wxpayConfig:list')")
    public ResponseEntity<Object> getWxpayConfigs(WxpayConfigQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(wxpayConfigService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增微信支付配置")
    @ApiOperation("新增微信支付配置")
    @PreAuthorize("@el.check('admin','wxpayConfig:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody WxpayConfig resources){
        return new ResponseEntity<>(wxpayConfigService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改微信支付配置")
    @ApiOperation("修改微信支付配置")
    @PreAuthorize("@el.check('admin','wxpayConfig:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody WxpayConfig resources){
        wxpayConfigService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除微信支付配置")
    @ApiOperation("删除微信支付配置")
    @PreAuthorize("@el.check('admin','wxpayConfig:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            wxpayConfigService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("支付")
    @ApiOperation("支付")
    @RequestMapping("/gowechatpay")
    @AnonymousAccess
    public Object goWeChatPay(@RequestParam("orderId") String orderId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return wxpayConfigService.goWeChatPay(orderId, request);
    }

    @Log("微信回调函数")
    @ApiOperation("微信回调函数")
    @RequestMapping("/wechatnotify")
    @AnonymousAccess
    public String weChatNotify(@RequestParam("orderId") String orderId, HttpServletRequest request) {
        String returnXML = null;
        try {
            returnXML =  wxpayConfigService.weChatNotify(orderId,request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnXML;
    }

}
