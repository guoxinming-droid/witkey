package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbEmploy;
import co.zhenxi.modules.shop.service.ZbEmployService;
import co.zhenxi.modules.shop.service.ZbShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.PhantomReference;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-11 15:37
 * @Description: EmployShopController
 **/

@AllArgsConstructor
@Api(tags = "雇佣服务商")
@RestController
@RequestMapping("/api/employShop")
public class EmployShopController {

    private final ZbShopService zbShopService;

    private final ZbEmployService zbEmployService;

    @Log("增添雇佣任务")
    @ApiOperation("增添雇佣任务")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/InsertEmploy")
    public ResponseEntity<Object> InsertEmploy( @RequestBody ZbEmploy zbEmploy, Integer [] localStorageIds) {
        zbEmployService.InsertEmploy(zbEmploy,localStorageIds);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
