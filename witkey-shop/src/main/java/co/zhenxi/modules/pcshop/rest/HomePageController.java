package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbTask;
import co.zhenxi.modules.shop.domain.ZbTaskAdvice;
import co.zhenxi.modules.shop.service.*;
import co.zhenxi.modules.shop.service.dto.ZbShopQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbTaskQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbUserDetailQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-31 16:16
 * @Description: HomePageController
 **/

@AllArgsConstructor
@Api(tags = "首页")
@RestController
@RequestMapping("/api/homePage")
public class HomePageController {

    private final ZbNavService zbNavService;

    private final ZbAdService zbAdService;

    private final ZbTaskService zbTaskService;

    private final ZbCateService zbCateService;

    private final ZbDistrictService zbDistrictService;

    private final ZbShopService zbShopService;

    private final ZbWorkService zbWorkService;

    private final ZbActivityService zbActivityService;

    private final ZbCashoutService zbCashoutService;

    private final ZbGoodsService zbGoodsService;

    private final ZbUserDetailService zbUserDetailService;

    private final ZbSuccessCaseService zbSuccessCaseService;

    private final ZbArticleService zbArticleService;

    private final ZbLinkService zbLinkService;

    private final ZbConfigService zbConfigService;


    @Log("获取指定广告位广告")
    @ApiOperation("获取指定广告位广告")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getAd")
    public ResponseEntity<Object> qureyAd(@RequestParam("targetId") Integer targetId, Integer pageSize) {
        return new ResponseEntity<>(zbAdService.queryAd(targetId,pageSize), HttpStatus.OK);
    }

    @Log("查询导航栏")
    @ApiOperation("查询导航栏")
    @GetMapping(value = "/queryAll")
    //@PreAuthorize("@el.check('admin','zbMessageTemplate:list')")
    @AnonymousAccess
    public ResponseEntity<Object> download() throws IOException {
        return new ResponseEntity<>(zbNavService.queryAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/TaskList")
    @Log("查询所有任务")
    @ApiOperation("查询所有任务")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getTasks(ZbTaskQueryCriteria criteria, @RequestBody Pageable pageable){
        return new ResponseEntity<>(zbTaskService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @GetMapping(value = "/ShopList")
    @Log("查询所有服务商")
    @ApiOperation("查询所有服务商")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getShops(ZbUserDetailQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbUserDetailService.queryAllByCateId(criteria,null,pageable),HttpStatus.OK);
    }

    @GetMapping(value = "/TaskListByCreateTime")
    @Log("查询所有任务time")
    @ApiOperation("查询所有任务time")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getByCreateTime(Timestamp createTime){
        return new ResponseEntity<>(zbTaskService.getByCreateTime(createTime),HttpStatus.OK);
    }


    @Log("查询行业")
    @ApiOperation("查询行业")
    //@PreAuthorize("@el.check('admin','zbCate:list')")
    @AnonymousAccess
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(zbCateService.getAll(),HttpStatus.OK);
    }

    @Log("获取地区")
    @ApiOperation("获取地区")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getDistrict")
    public ResponseEntity<Object> getDistrict() {
        return new ResponseEntity<>(zbDistrictService.getDistrict(),HttpStatus.OK);
    }

    @Log("一键发布任务")
    @ApiOperation("一键发布任务")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @PostMapping("/releaseTaskByEasy")
    public ResponseEntity<Object> releaseTaskByEasy(@RequestBody ZbTask zbTask) {
        //看书v库又没这个用户 没有给他组测
        zbTaskService.insert(zbTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("普通发布任务")
    @ApiOperation("普通发布任务")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @PostMapping("/releaseTask")
    public ResponseEntity<Object> releaseTaskByNormal(@RequestBody ZbTaskAdvice zbTask, @RequestParam("ids") String [] ids) {
        if(ids ==null){
            ids = new String [0];
        }

        Integer[] integers = new Integer[ids.length];
        for (int i = 0; i < ids.length; i++) {
            integers[i] = Integer.parseInt(ids[i]);
        }
        zbTaskService.releaseTask(zbTask,integers);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Log("获取中标消息")
    @ApiOperation("获取中标消息")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getBidWork")
    public Map<String,Object> getBidWork( Pageable size){
        /**
         * 用户名称 中标得任务 金额 时间
         */
        return zbWorkService.getWorkAll(size);
    }

    @Log("获取活动列表")
    @ApiOperation("获取活动列表")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getActivity")
    public Map<String,Object> getActivity( Pageable size){
        return zbActivityService.getActivity(size);
    }

    @Log("获取提现列表")
    @ApiOperation("获取提现列表")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getCashout")
    public Map<String,Object> getCashOut( @PageableDefault(sort = "zbc.created_at") Pageable size){
        /**
         * 用户名称 提现金额 时间
         */
        return zbCashoutService.getCashout(size);
    }

    @Log("获取作品和服务")
    @ApiOperation("获取作品和服务")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getGoods")
    public Map<String,Object> getGoods( @PageableDefault(sort = "zbg.view_num") Pageable size, Integer type){
        /**
         * 服务和作品
         */
        return zbGoodsService.getGoods(size,type);
    }

    @Log("获取VIP店铺")
    @ApiOperation("获取VIP店铺")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getShopByVip")
    public Map<String,Object> getVipShop( @PageableDefault(sort = "zb_shop_package.package_id") Pageable size){
        /**
         * 获取VIP店铺
         */
        return zbShopService.getShopByVip(size);
    }

    @Log("推荐店铺")
    @ApiOperation("推荐店铺")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getRecommendShop")
    public Map<String,Object> getRecommendShop( @PageableDefault(sort = " zs.good_comment") Pageable size){
        /**
         * 推荐店铺
         */
        return zbShopService.getRecommendShop(size);
    }

    @Log("成功案例")
    @ApiOperation("成功案例")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getSuccessStories")
    public Map<String,Object> getSuccessStories( @PageableDefault(sort = " view_count") Pageable size){
        /**
         * 成功案例
         */
        return zbSuccessCaseService.getSuccessStories(size);
    }

    @Log("获取资讯文章")
    @ApiOperation("获取资讯文章")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getArticle")
    public Map<String,Object> getArticle( @PageableDefault(sort = " description") Pageable size){
        /**
         * 获取资讯文章
         */
        return zbArticleService.getArticle(size);
    }

    @Log("获取友情链接")
    @ApiOperation("获取友情链接")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getLink")
    public Map<String,Object> getLink( @PageableDefault(sort = " sort") Pageable size){
        /**
         * 获取资讯文章
         */
        return zbLinkService.getLink(size);
    }

    @Log("获取页脚数据")
    @ApiOperation("获取页脚数据")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getFooter")
    public  ResponseEntity<Object> getFooter( String type){
        /**
         * 获取资讯文章
         */
        return new ResponseEntity<>(zbConfigService.getZbSiteBy(type),HttpStatus.OK);
    }


}
