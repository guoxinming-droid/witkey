package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbUsers;
import co.zhenxi.modules.shop.service.ZbGoodsService;
import co.zhenxi.modules.shop.service.dto.ZbGoodsQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-11 16:52
 * @Description: ZbShopController
 **/
@AllArgsConstructor
@Api(tags = "威客商城")
@RestController
@RequestMapping("/api/witkeyShop")
public class ShopController {


    private final ZbGoodsService zbGoodsService;


    @Log("获取商品分类")
    @ApiOperation("获取商品分类")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoodsType")
    public ResponseEntity<Object> getGoodsType() throws Exception {

        return new ResponseEntity<>( zbGoodsService.getGoodsType(),HttpStatus.OK);
    }


    @Log("商品条件查询")
    @ApiOperation("商品条件查询")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getGoods")
    public ResponseEntity<Object> getGoods(ZbGoodsQueryCriteria zbGoodsQueryCriteria , Pageable pageable) throws Exception {
        return new ResponseEntity<>( zbGoodsService.queryAll(zbGoodsQueryCriteria,pageable),HttpStatus.OK);
    }


}
