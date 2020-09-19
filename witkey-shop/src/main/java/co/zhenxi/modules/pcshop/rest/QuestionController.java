package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.service.ZbQuestionService;
import co.zhenxi.modules.shop.service.dto.ZbQuestionQueryCriteria;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-16 17:27
 * @Description: QuestionController
 **/

@AllArgsConstructor
@Api(tags = "Vip页面")
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final ZbQuestionService zbQuestionService;


    @Log("获取问题列表")
    @ApiOperation("获取问题列表")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getQuestionByStatus")
    public ResponseEntity<Object> getQuestionByStatus(ZbQuestionQueryCriteria zbQuestionQueryCriteria,
                                              @PageableDefault(sort = "time",direction = Sort.Direction.DESC) Pageable pageable){
        if(zbQuestionQueryCriteria.getStatus()==null){
            zbQuestionQueryCriteria.setStatus(1);
        }

        return new ResponseEntity<>(zbQuestionService.queryAll(zbQuestionQueryCriteria,pageable), HttpStatus.OK);
    }

    @Log("获取全部问题列表")
    @ApiOperation("获取全部问题列表")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getQuestion")
    public ResponseEntity<Object> getQuestion(Integer status,@PageableDefault(sort = "time",direction = Sort.Direction.DESC) Pageable pageable){


        return new ResponseEntity<>(zbQuestionService.getQuestion(status,pageable), HttpStatus.OK);
    }

    @Log("获取回答得问题量")
    @ApiOperation("获取回答得问题量")
    //@PreAuthorize("@el.check('admin','zbActivity:del')")
    @AnonymousAccess
    @GetMapping("/getQuestionCount")
    public ResponseEntity<Object> getQuestionCount(){


        return new ResponseEntity<>(zbQuestionService.getQuestionCount(), HttpStatus.OK);
    }
}
