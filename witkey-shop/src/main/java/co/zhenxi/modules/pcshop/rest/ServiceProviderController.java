package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.service.ZbGoodsCommentService;
import co.zhenxi.modules.shop.service.ZbGoodsService;
import co.zhenxi.modules.shop.service.ZbShopOrderService;
import co.zhenxi.modules.shop.service.ZbShopService;
import co.zhenxi.modules.shop.service.dto.ZbGoodsCommentQueryCriteria;
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

    private final ZbShopService zbShopService;

    private final ZbGoodsService zbGoodsService;

    private final ZbGoodsCommentService zbGoodsCommentService;

    private final ZbShopOrderService zbShopOrderService;


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
    @GetMapping("/getServiceProviderById")
    public ResponseEntity<Object> getServiceProviderById(Integer shopId) throws Exception {
        return new ResponseEntity<>(zbShopService.queryAllById(shopId), HttpStatus.OK);
    }

    @Log("服务商作品及服务")
    @ApiOperation("服务商作品及服务")
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

    @Log("获取店铺评价")
    @ApiOperation("获取店铺评价")
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

    @Log("店铺好评差评")
    @ApiOperation("店铺好评差评")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoodsScoreByShopIdAndType")
    public ResponseEntity<Object> getGoodsScoreByShopIdAndType(@RequestParam("shopId") Integer shopId,Integer type) throws Exception {
        return new ResponseEntity<>(zbGoodsService.getGoodsScoreByShopIdAndType(shopId,type), HttpStatus.OK);
    }


//    @Log("获取店铺详情")
//    @ApiOperation("获取店铺详情")
//    //@PreAuthorize("@el.check('admin','zbAd:del')")
//    @AnonymousAccess
//    @GetMapping("/getShopById")
//    public ResponseEntity<Object> getShopById(@RequestParam("shopId") Integer shopId) throws Exception {
//        return new ResponseEntity<>(zbShopService.getShopById(shopId), HttpStatus.OK);
//    }

    @Log("作品服务详情")
    @ApiOperation("作品服务详情")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoodsByType")
    public ResponseEntity<Object> getGoodsByType(@RequestParam("goodsId")Integer goodsId) throws Exception {
        return new ResponseEntity<>(zbGoodsService.getGoodsByType(goodsId), HttpStatus.OK);
    }

    @Log("作品服务评价")
    @ApiOperation("作品服务评价")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoodsByType/comment")
    public ResponseEntity<Object> getGoodsCommentById(ZbGoodsCommentQueryCriteria zbGoodsCommentQueryCriteria,
                                                       Pageable pageable) throws Exception {
        Integer type = zbGoodsCommentQueryCriteria.getType();
        if(type == null){
            zbGoodsCommentQueryCriteria.setType(0);
        }
        return new ResponseEntity<>(zbGoodsCommentService.queryAllAndComment(zbGoodsCommentQueryCriteria,pageable), HttpStatus.OK);
    }


    @Log("店铺其他作品")
    @ApiOperation("店铺其他作品")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoodsNotByGoodsId")
    public ResponseEntity<Object> getGoodsByGoodsId(@RequestParam("goodsId") Integer goodsId,Pageable pageable) throws Exception {
        return new ResponseEntity<>(zbGoodsService.getGoodsByGoodsId(goodsId,pageable), HttpStatus.OK);
    }

    @Log("提交订单前回显")
    @ApiOperation("提交订单前回显")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/GoodsPutOrder")
    public ResponseEntity<Object> GoodsPutOrder(@RequestParam("goodsId") Integer[] goodsIds,Pageable pageable) throws Exception {
//        for (int i = 0; i < goodsIds.length; i++) {
//            System.out.println(goodsIds[i]);
//        }
        return new ResponseEntity<>(zbGoodsService.GoodsPutOrder(goodsIds,pageable), HttpStatus.OK);
    }

    @Log("收藏店铺")
    @ApiOperation("收藏店铺")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/CollectionShop")
    public ResponseEntity<Object> CollectionShop(@RequestParam("uid") Integer uid,@RequestParam("shopId") Integer shopId) throws Exception {
        zbShopService.CollectionShop(uid,shopId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
