package co.zhenxi.modules.applets;


import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.*;
import co.zhenxi.modules.shop.service.ZbCateService;
import co.zhenxi.modules.shop.service.ZbGoodsService;
import co.zhenxi.modules.shop.service.ZbShopService;
import co.zhenxi.modules.shop.service.ZbTaskService;
import co.zhenxi.modules.shop.service.dto.ZbGoodsQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbShopQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbTaskQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbSuccessCaseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@Api(tags = "首页")
@RestController
@RequestMapping("api")
public class HomeController {


    private final ZbCateService zbCateService;
    private final IGenerator generator;
    private final ZbTaskService zbTaskService;
    private final ZbShopService zbShopService;
    private final ZbGoodsService zbGoodsService;
    private final ZbSuccessCaseMapper zbSuccessCaseMapper;

    @GetMapping("/getZbCatesList")
    @Log("查询行业")
    @ApiOperation("查询行业")
    //@PreAuthorize("@el.check('admin','zbCate:list')")
    @AnonymousAccess
    public List<ZbCate> getZbCatesList(Integer fid){
        return zbCateService.getZbCatesList(fid);
    }



    @GetMapping(value = "/getTaskList")
    @Log("查询任务列表")
    @ApiOperation("查询任务列表")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getTaskList(ZbTaskQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbTaskService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/getRecommendShopList")
    @Log("查询推荐商城首页推荐列表")
    @ApiOperation("查询推荐商城首页推荐列表")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getRecommendShopList(ZbShopQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbShopService.getRecommendShopList(criteria,pageable), HttpStatus.OK);
    }


    @GetMapping(value = "/getRecommendGoodsList")
    @Log("查询服务商首页推荐列表")
    @ApiOperation("查询服务商首页推荐列表")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getRecommendServiceList(ZbGoodsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbGoodsService.getRecommendGoodsList(criteria,pageable), HttpStatus.OK);
    }




    @GetMapping(value = "/getRecommendShopListById")
    @Log("查询推荐商城首页推荐详情")
    @ApiOperation("查询推荐商城首页推荐详情")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ZbShop getRecommendShopListById(Integer id){
        return zbShopService.getRecommendShopListById(id);
    }

    @GetMapping(value = "/getGoodsByShopId")
    @Log("查询推荐商城下商品列表")
    @ApiOperation("查询推荐商城下商品列表")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public List<ZbGoods> getGoodsByShopId(Integer id , Integer type){
        return zbGoodsService.selectGoodsByShopId(id,type);
    }


    @GetMapping(value = "/getSuccessCaseyUId")
    @Log("查询推荐商城案例列表")
    @ApiOperation("查询推荐商城案例列表")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public List<ZbSuccessCase> getSuccessCaseyUId(Integer uid){
        String wheresql= "where 1 =1";
        if(uid!=null && !"".equals(uid)){
            wheresql+="and uid ="+uid;
        }
        return zbSuccessCaseMapper.getSuccessCaseyUId(wheresql);
    }


    @GetMapping(value = "/getGoodsCommentByGoodId")
    @Log("查询商品评价列表")
    @ApiOperation("查询商品评价列表")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getGoodsCommentByGoodId(Integer id,Integer type){
        return new ResponseEntity<>(zbGoodsService.getGoodsCommentByGoodId(id,type), HttpStatus.OK);
    }




}
