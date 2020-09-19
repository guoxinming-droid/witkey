package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbArticle;
import co.zhenxi.modules.shop.service.ZbArticleService;
import co.zhenxi.modules.shop.service.ZbWebService;
import co.zhenxi.modules.shop.service.dto.ZbArticleQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbWebQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-15 17:55
 * @Description: WebContoller
 **/
@AllArgsConstructor
@Api(tags = "Vip页面")
@RestController
@RequestMapping("/api/article")
public class ArticleContoller {

    private ZbArticleService zbArticleService;

    @Log("获取资讯")
    @ApiOperation("获取资讯")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getArticle")
    public ResponseEntity<Object> getArticle(ZbArticleQueryCriteria zbArticleQueryCriteria, @PageableDefault(sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        if(zbArticleQueryCriteria.getCatIds()==null){
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(55);
                zbArticleQueryCriteria.setCatId(integers);
        }
        return new ResponseEntity<>(zbArticleService.queryAll(zbArticleQueryCriteria,pageable), HttpStatus.OK);
    }

    @Log("获取资讯标题")
    @ApiOperation("获取资讯标题")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getArticleTitle")
    public ResponseEntity<Object> getArticleTitle(@RequestParam("articleId") Integer articleId ) throws Exception {

        return new ResponseEntity<>(zbArticleService.getArticleTitle(articleId), HttpStatus.OK);
    }

    @Log("相关资讯")
    @ApiOperation("获取资讯标题")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getRelevantArticleTitle")
    public ResponseEntity<Object> getRelevantArticleTitle(@RequestParam("articleId") Integer articleId ) throws Exception {

        return new ResponseEntity<>(zbArticleService.getRelevantArticleTitle(articleId), HttpStatus.OK);
    }
}
