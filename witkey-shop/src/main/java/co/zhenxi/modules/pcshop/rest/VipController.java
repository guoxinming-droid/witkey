package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbInterview;
import co.zhenxi.modules.shop.domain.ZbVipshopOrder;
import co.zhenxi.modules.shop.service.ZbInterviewService;
import co.zhenxi.modules.shop.service.ZbPrivilegesService;
import co.zhenxi.modules.shop.service.ZbShopPackageService;
import co.zhenxi.modules.shop.service.ZbVipshopOrderService;
import co.zhenxi.modules.shop.service.dto.ZbInterviewQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbVipshopOrderMapper;
import co.zhenxi.tools.utils.AlipayUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-14 17:57
 * @Description: VipController
 **/
@AllArgsConstructor
@Api(tags = "Vip页面")
@RestController
@RequestMapping("/api/VipPage")
public class VipController {

    private final ZbPrivilegesService zbPrivilegesService;

    private final ZbInterviewService zbInterviewService;

    private final ZbVipshopOrderService zbVipshopOrderService;

    private final ZbShopPackageService zbShopPackageService;





    @Log("查询特权内容")
    @ApiOperation("查询特权内容")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getPrivileges")
    public ResponseEntity<Object> getPrivileges() throws Exception {
        return new ResponseEntity<>(zbPrivilegesService.getPrivileges(), HttpStatus.OK);
    }

    @Log("获取vip访谈")
    @ApiOperation("获取vip访谈")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getInterview")
    public ResponseEntity<Object> getInterview() throws Exception {
        return new ResponseEntity<>(zbInterviewService.getInterview(), HttpStatus.OK);
    }

    @Log("vip功能介绍")
    @ApiOperation("vip功能介绍")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getVipInfo")
    public ResponseEntity<Object> getVipInfo() throws Exception {
        return new ResponseEntity<>(zbPrivilegesService.getVipInfo(), HttpStatus.OK);
    }


    /**
     * 不使用的uri
     * @param packageId
     * @param uid
     * @param time
     * @return
     * @throws Exception
     */
    @Log("支付订单get参数")
    @ApiOperation("支付订单get参数")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/VipOrder")
    public ResponseEntity<Object> VipOrder(@RequestParam Integer packageId,@RequestParam Integer uid ,@RequestParam Integer time) throws Exception {

        //看此用户有没有支付
        ZbVipshopOrder byUId = zbVipshopOrderService.getByUId(uid);
        if(byUId == null){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        if(byUId.getTimePeriod() != time){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        if(byUId.getPackageId()!= packageId){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        zbShopPackageService.insert(packageId,uid,time);
        return new ResponseEntity<>( HttpStatus.OK);
    }


    /**
     * 不使用的uri
     * @param code
     * @return
     * @throws Exception
     */
    @Log("支付订单参数为订单编号")
    @ApiOperation("支付订单参数为订单编号")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/VipOrderByCode")
    public ResponseEntity<Object> VipOrder(@RequestParam("code") String code) throws Exception {
        System.out.println(AlipayUtils.getOrderCode());
        ZbVipshopOrder byCode = zbVipshopOrderService.getByCode(code);
        if(byCode == null){
            return new ResponseEntity<>("订单不存在",HttpStatus.BAD_REQUEST);
        }
        zbShopPackageService.insert(byCode);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Log("vip访谈")
    @ApiOperation("vip访谈")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/interviewByVip")
    public ResponseEntity<Object> interviewByVip(@PageableDefault(sort = "created_at",direction = Sort.Direction.DESC) Pageable pageable){

        return new ResponseEntity<>( zbInterviewService.getInterviewByVip(pageable),HttpStatus.OK);
    }

    @Log("生成订单")
    @ApiOperation("生成订单")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/insertVipShopOrder")
    public ResponseEntity<Object> interviewByVip(@RequestBody ZbVipshopOrder zbVipshopOrder){
        zbVipshopOrder.setCode(AlipayUtils.getOrderCode());
        zbVipshopOrder.setStatus(0);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        zbVipshopOrder.setCreatedAt(timestamp);
        zbVipshopOrder.setUpdatedAt(timestamp);
        return new ResponseEntity<>( zbVipshopOrderService.generateOrder(zbVipshopOrder),HttpStatus.OK);
    }

    @Log("修改订单状态")
    @ApiOperation("修改订单状态")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/updateVipOrder")
    public ResponseEntity<Object> updateVipOrder(@RequestParam("tradeNo") String tradeNo) throws Exception {
        return new ResponseEntity<>( zbVipshopOrderService.updateByCode(tradeNo),HttpStatus.OK);
    }

    @Log("vip功能介绍")
    @ApiOperation("vip功能介绍")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getVipInfoT")
    public ResponseEntity<Object> getVipInfoT() throws Exception {
        return new ResponseEntity<>(zbPrivilegesService.getVipInfoT(), HttpStatus.OK);
    }


    @Log("vip套餐详情")
    @ApiOperation("vip套餐详情")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getVipInfoDetails")
    public ResponseEntity<Object> getVipInfoDetails(@RequestParam("packageId") Integer packageId) throws Exception {
        return new ResponseEntity<>(zbPrivilegesService.getVipInfoDetails(packageId), HttpStatus.OK);
    }

}
