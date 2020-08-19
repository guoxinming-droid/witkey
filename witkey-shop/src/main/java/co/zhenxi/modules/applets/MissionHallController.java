package co.zhenxi.modules.applets;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.service.ZbTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@Api(tags = "大厅")
@RestController
@RequestMapping("api")
public class MissionHallController {

    private final ZbTaskService zbTaskService;

    @GetMapping(value = "/getHallTaskList")
    @Log("查询任务大厅列表")
    @ApiOperation("查询任务大厅列表")
    //   @PreAuthorize("@el.check('admin','XSZbTasks:list')")
    @AnonymousAccess
    public ResponseEntity<Object> getTaskHallList(Integer typeId,Integer cateId,  Pageable pageable){
        return new ResponseEntity<>(zbTaskService.getTaskHallList(typeId,cateId,pageable), HttpStatus.OK);
    }

}
