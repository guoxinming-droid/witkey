package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbSuccessCase;
import co.zhenxi.modules.shop.service.ZbInterviewService;
import co.zhenxi.modules.shop.service.ZbPackageService;
import co.zhenxi.modules.shop.service.ZbPrivilegesService;
import co.zhenxi.modules.shop.service.ZbSuccessCaseService;
import co.zhenxi.modules.shop.service.dto.ZbPackageQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbShopQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbSuccessCaseQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-14 10:36
 * @Description: SuccessCaseController
 **/
@AllArgsConstructor
@Api(tags = "成功案例页面")
@RestController
@RequestMapping("/api/Successcase")
public class SuccessCaseController {
    private final ZbSuccessCaseService zbSuccessCaseService;

    private final ZbPackageService zbPackageService;




    @Log("条件搜索成功案例")
    @ApiOperation("条件搜索成功案例")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getServiceProvider")
    public ResponseEntity<Object> getServiceProvider(ZbSuccessCaseQueryCriteria zbSuccessCaseQueryCriteria, Pageable pageable) throws Exception {
        return new ResponseEntity<>(zbSuccessCaseService.queryAll(zbSuccessCaseQueryCriteria,pageable), HttpStatus.OK);
    }

    @Log("条件搜索成功案例CatePid")
    @ApiOperation("条件搜索成功案例CatePid")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getServiceProviderByCatePid")
    public ResponseEntity<Object> getServiceProviderByCatePid(Integer catePid, Pageable pageable) throws Exception {
        return new ResponseEntity<>(zbSuccessCaseService.getServiceProviderByCatePid(catePid,pageable), HttpStatus.OK);
    }


    @Log("查询套餐")
    @ApiOperation("查询套餐")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getPackage")
    public ResponseEntity<Object> getPackage() throws Exception {
        return new ResponseEntity<>(zbPackageService.getPackage(), HttpStatus.OK);
    }


}
