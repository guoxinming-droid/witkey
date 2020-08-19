/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbConfig;
import co.zhenxi.modules.shop.domain.ZbQuestion;
import co.zhenxi.modules.shop.service.ZbConfigService;
import co.zhenxi.modules.shop.service.ZbQuestionService;
import co.zhenxi.modules.shop.service.dto.ZbQuestionDto;
import co.zhenxi.modules.shop.service.dto.ZbQuestionQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
* @author guoke
* @date 2020-08-06
*/
@AllArgsConstructor
@Api(tags = "问答管理管理")
@RestController
@RequestMapping("/api/zbQuestion")
public class ZbQuestionController {

    private final ZbQuestionService zbQuestionService;
    private final IGenerator generator;
    private final ZbConfigService zbConfigService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbQuestion:list')")
    public void download(HttpServletResponse response, ZbQuestionQueryCriteria criteria) throws IOException {
        zbQuestionService.download(generator.convert(zbQuestionService.queryAll(criteria), ZbQuestionDto.class), response);
    }

    @GetMapping
    @Log("查询问答管理")
    @ApiOperation("查询问答管理")
    @PreAuthorize("@el.check('admin','zbQuestion:list')")
    public ResponseEntity<Object> getZbQuestions(ZbQuestionQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbQuestionService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增问答管理")
    @ApiOperation("新增问答管理")
    @PreAuthorize("@el.check('admin','zbQuestion:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbQuestion resources){
        return new ResponseEntity<>(zbQuestionService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改问答管理")
    @ApiOperation("修改问答管理")
    @PreAuthorize("@el.check('admin','zbQuestion:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbQuestion resources){
        zbQuestionService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除问答管理")
    @ApiOperation("删除问答管理")
    @PreAuthorize("@el.check('admin','zbQuestion:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbQuestionService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getZbQuestionsList")
    @Log("查询问答管理")
    @ApiOperation("查询问答管理")
   // @PreAuthorize("@el.check('admin','zbQuestion:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getZbQuestionsList( String userName,
                                                      Integer category,
                                                      Integer status,
                                                      String startTime,
                                                      String endTime,
                                                     Pageable pageable){
        return new ResponseEntity<>(zbQuestionService.getZbQuestionsList(userName,category,status,startTime,endTime,pageable),HttpStatus.OK);
    }

    @Log("问答审核")
    @ApiOperation(value = "问答审核")
    @PostMapping(value = "/verify")
    @AnonymousAccess
    public Boolean verify(@RequestParam Integer id,@RequestParam Integer status){
        return  zbQuestionService.onStatus(id,status);
    }

    @GetMapping("/getQuestionConfig")
    @Log("查询雇佣配置")
    @ApiOperation("查询雇佣配置")
    //  @PreAuthorize("@el.check('admin','zbConfig:list')")
    @AnonymousAccess
    @ResponseBody
    public List<ZbConfig> getQuestionConfig(@RequestParam String type){
        return zbConfigService.getZbSiteBy(type);
    }



    @PutMapping("/updateQuestionConfig")
    @Log("修改雇佣配置")
    @ApiOperation("修改雇佣配置")
    @AnonymousAccess
    public ResponseEntity<Object> updateQuestionConfig(@Validated  List<ZbConfig> zbConfig){
        for(ZbConfig  Config:zbConfig) {
            zbConfigService.updateById(Config);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
