package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbTask;
import co.zhenxi.modules.shop.domain.ZbWork;
import co.zhenxi.modules.shop.service.*;
import co.zhenxi.modules.shop.service.dto.ZbTaskQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-04 10:24
 * @Description: TaskPageController
 **/
@AllArgsConstructor
@Api(tags = "任务大厅")
@RestController
@RequestMapping("/api/taskPage")
public class TaskPageController {

    private final ZbTaskService zbTaskService;

    private final ZbTaskTypeService zbTaskTypeService;

    private final ZbCateService zbCateService;

    private final ZbDistrictService zbDistrict;

    private final ZbUsersService zbUsersService;

    private final ZbWorkService zbWorkService;


    @Log("获取任务类型")
    @ApiOperation("获取任务类型")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getTaskType")
    public ResponseEntity<Object> getTaskType() {
        return new ResponseEntity<>(zbTaskTypeService.queryAll(null), HttpStatus.OK);
    }

    @Log("获取任务标签类型")
    @ApiOperation("获取任务标签类型")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getCateType")
    public ResponseEntity<Object> getCateType(Integer pid) {
        return new ResponseEntity<>(zbCateService.getCateType(pid), HttpStatus.OK);
    }

    @Log("获取地区")
    @ApiOperation("获取地区")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getDistrict")
    public ResponseEntity<Object> getDistrict(Integer pid ,Integer size){
        return new ResponseEntity<>(zbDistrict.getDistrictById(pid,size), HttpStatus.OK);
    }

    @Log("条件查询")
    @ApiOperation("条件查询")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getTaskBySome")
    public Map getTaskBySome(ZbTaskQueryCriteria zbTaskQueryCriteria, Pageable pageable){
        return zbTaskService.queryAll(zbTaskQueryCriteria,  pageable);
    }

    @Log("收藏任务")
    @ApiOperation("收藏任务")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/collectionTask")
    public ResponseEntity<Object> collectionTask(Integer taskId,Integer uId){
        zbTaskService.collectionTask(taskId,  uId);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Log("获取任务详情")
    @ApiOperation("获取任务详情")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/getTaskDetails")
    public ResponseEntity<Object> getTaskDetails(Integer taskId){
        return new ResponseEntity<>(zbTaskService.getTasksById(taskId), HttpStatus.OK);
    }


    @Log("数据回显")
    @ApiOperation("数据回显")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/getTaskToEcho")
    public ZbTask getTaskToEcho(@RequestBody ZbTask zbTask,Integer uid){
        if(zbTask!=null){
            return zbTask;
        }
        return null;
    }
    @Log("提交稿件")
    @ApiOperation("提交稿件")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/subWork")
    public Map subWork(@RequestBody ZbWork zbWork){
        return zbWorkService.insert(zbWork);
    }




}
