/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.rest;
import java.util.Arrays;
import co.zhenxi.dozer.service.IGenerator;
import lombok.AllArgsConstructor;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.tools.domain.Ordertest;
import co.zhenxi.tools.service.OrdertestService;
import co.zhenxi.tools.service.dto.OrdertestQueryCriteria;
import co.zhenxi.tools.service.dto.OrdertestDto;
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
* @author gxm
* @date 2020-07-18
*/
@AllArgsConstructor
@Api(tags = "支付测试管理")
@RestController
@RequestMapping("/api/ordertest")
public class OrdertestController {

    private final OrdertestService ordertestService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','ordertest:list')")
    public void download(HttpServletResponse response, OrdertestQueryCriteria criteria) throws IOException {
        ordertestService.download(generator.convert(ordertestService.queryAll(criteria), OrdertestDto.class), response);
    }

    @GetMapping
    @Log("查询支付测试")
    @ApiOperation("查询支付测试")
    @PreAuthorize("@el.check('admin','ordertest:list')")
    public ResponseEntity<Object> getOrdertests(OrdertestQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(ordertestService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增支付测试")
    @ApiOperation("新增支付测试")
    @PreAuthorize("@el.check('admin','ordertest:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Ordertest resources){
        return new ResponseEntity<>(ordertestService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改支付测试")
    @ApiOperation("修改支付测试")
    @PreAuthorize("@el.check('admin','ordertest:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Ordertest resources){
        ordertestService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除支付测试")
    @ApiOperation("删除支付测试")
    @PreAuthorize("@el.check('admin','ordertest:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            ordertestService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
