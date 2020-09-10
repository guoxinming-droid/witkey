package co.zhenxi.modules.shop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.service.ZbDistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-28 16:20
 * @Description: ZbDistrictController
 **/
@AllArgsConstructor
@Api(tags = "地区管理")
@RestController
@RequestMapping("/api/zbDistrict")
public class ZbDistrictController {

    private final ZbDistrictService zbDistrictService;



    @Log("获取地区")
    @ApiOperation("获取地区")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getDistrict")
    public ResponseEntity<Object> getDistrict() {
        return new ResponseEntity<>(zbDistrictService.getDistrict(),HttpStatus.OK);
    }
}
