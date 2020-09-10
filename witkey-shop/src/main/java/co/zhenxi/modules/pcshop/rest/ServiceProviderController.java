package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.service.ZbGoodsService;
import co.zhenxi.modules.shop.service.ZbShopService;
import co.zhenxi.modules.shop.service.dto.ZbShopQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-08 14:36
 * @Description: ServiceProviderController
 **/
@AllArgsConstructor
@Api(tags = "服务商")
@RestController
@RequestMapping("/api/serviceProvider")
public class ServiceProviderController {

    private  final ZbShopService zbShopService;

    private final ZbGoodsService zbGoodsService;


    @Log("条件搜索服务商")
    @ApiOperation("条件搜索服务商")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/getServiceProvider")
    public ResponseEntity<Object> getServiceProvider(
            //店铺的标签 和 地址 搜索条件
            ZbShopQueryCriteria zbShopQueryCriteria,
            Pageable pageable) throws Exception {
        return new ResponseEntity<>(zbShopService.queryAll(zbShopQueryCriteria,pageable), HttpStatus.OK);
    }

    @Log("服务商详情页")
    @ApiOperation("服务商详情页")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/getServiceProviderById")
    public ResponseEntity<Object> getServiceProviderById(Integer shopId) throws Exception {
        return new ResponseEntity<>(zbShopService.queryAllById(shopId), HttpStatus.OK);
    }

    @Log("服务商作品")
    @ApiOperation("服务商作品")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoodsById")
    public ResponseEntity<Object> getGoodsById(@RequestParam("shopId") String shopId, @RequestParam("type")String type,String cateId, Pageable pageable) throws Exception {

        return new ResponseEntity<>(zbShopService.getWorkById(shopId,type,cateId,pageable), HttpStatus.OK);
    }

    @Log("服务商成功案例")
    @ApiOperation("服务商成功案例")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getSuccessCaseById")
    public ResponseEntity<Object> getSuccessCaseById(@RequestParam("shopId") String shopId,String cateId ,Pageable pageable) throws Exception {

        return new ResponseEntity<>(zbShopService.getSuccessCaseById(shopId,cateId,pageable), HttpStatus.OK);
    }

    @Log("服务商作品和服务计数")
    @ApiOperation("服务商作品和服务计数")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoodsCount")
    public ResponseEntity<Object> getGoodsCount(@RequestParam("shopId") String shopId, @RequestParam("type")String type,Pageable pageable) throws Exception {

        return new ResponseEntity<>(zbShopService.getGoodsCount(shopId,type,pageable), HttpStatus.OK);
    }

    @Log("成功案例计数")
    @ApiOperation("成功案例计数")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getSuccessCaseCount")
    public ResponseEntity<Object> getSuccessCaseCount(@RequestParam("shopId") String shopId,Pageable pageable) throws Exception {

        return new ResponseEntity<>(zbShopService.getSuccessCaseCount(shopId,pageable), HttpStatus.OK);
    }

    @Log("成功案例计数")
    @ApiOperation("成功案例计数")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getEvaluateByShopId")
    public ResponseEntity<Object> getEvaluateByShopId(@RequestParam("shopId") String shopId,Pageable pageable) throws Exception {

        return new ResponseEntity<>(zbShopService.getEvaluateByShopId(shopId,pageable), HttpStatus.OK);
    }


    @Log("店铺作品及服务数量")
    @ApiOperation("店铺作品及服务数量")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoodsCountByShopId")
    public ResponseEntity<Object> getGoodsCountByShopId(@RequestParam("shopId") Integer shopId) throws Exception {

        return new ResponseEntity<>(zbGoodsService.getGoodsCountByShopId(shopId), HttpStatus.OK);
    }

    @Log("店铺评分")
    @ApiOperation("店铺评分")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoodsScoreByShopId")
    public ResponseEntity<Object> getGoodsScoreByShopId(@RequestParam("shopId") Integer shopId) throws Exception {

        return new ResponseEntity<>(zbGoodsService.getGoodsScoreByShopId(shopId), HttpStatus.OK);
    }

}
