/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbTask;
import co.zhenxi.modules.shop.service.dto.ZbTaskDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    ZbTask getTasksById(long id);

    ZbTask getTasksWorkById(long id);


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(ZbTaskQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbTaskDto>
     */
    List<ZbTask> queryAll(ZbTaskQueryCriteria criteria);


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


}
