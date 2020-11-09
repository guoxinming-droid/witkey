/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbTask;
import co.zhenxi.modules.shop.domain.ZbTaskAdvice;
import co.zhenxi.modules.shop.service.dto.ZbTaskDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
* @author Guoxm
* @date 2020-07-16
*/
public interface ZbTaskService  extends BaseService<ZbTask>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryXSAll(ZbTaskQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbTaskDto>
    */
    List<ZbTask> queryXSAll(ZbTaskQueryCriteria criteria);



    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryZBAll(ZbTaskQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbTaskDto>
     */
    List<ZbTask> queryZBAll(ZbTaskQueryCriteria criteria);


    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbTaskDto> all, HttpServletResponse response) throws IOException;


    /**
     * 任务详情
     * @param id
     * @return
     */
    ZbTaskAdvice getTasksById(long id);

    ZbTask getTasksWorkById(long id);


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> getTaskList(ZbTaskQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbTaskDto>
     */
    List<ZbTask> getTaskList(ZbTaskQueryCriteria criteria);


    /**
     * 查询查询任务大厅列表数据分页
     * @param typeId 条件
     *  @param   cateId
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> getTaskHallList(Integer typeId,Integer cateId, Pageable pageable);

    /**
     * 查询查询任务大厅列表数据不分页
     * @param typeId 条件
     *  @param   cateId
     * @return List<ZbTaskDto>
     */
    List<ZbTask> getTaskHallList(Integer typeId,Integer cateId);

    /**
     * 查询收藏任务列表
     * @param uid 用户id
     * @return
     */
    List<ZbTaskAdvice> getCollectTask(Integer uid);

    /**
     * 查询所有数据分页
     * @param createTime ss
     * @return List<ZbTaskDto>
     */
    List<ZbTask> getByCreateTime(Timestamp createTime);

    /**
     * 一键新增任务
     */
    void insert(ZbTask zbTask);

    /**
     * 普通发布任务
     * @param ids 附件件Id
     */
    void releaseTask(ZbTaskAdvice zbTask,Integer[] ids);

    /**
     * 收藏任务
     * @param taskId 任务id
     * @param uId 用户id
     */
    void collectionTask(Integer taskId, Integer uId);

    List<ZbTask> getTasksWorkByIdPC(long taskId);

    Map<String,Object> queryAll1(ZbTaskQueryCriteria zbTaskQueryCriteria, Pageable pageable);
}
