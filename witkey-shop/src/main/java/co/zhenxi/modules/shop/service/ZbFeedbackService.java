/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbFeedback;
import co.zhenxi.modules.shop.service.dto.ZbFeedbackDto;
import co.zhenxi.modules.shop.service.dto.ZbFeedbackQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author Guo xinming
* @date 2020-07-16
*/
public interface ZbFeedbackService  extends BaseService<ZbFeedback>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbFeedbackQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbFeedbackDto>
    */
    List<ZbFeedback> queryAll(ZbFeedbackQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbFeedbackDto> all, HttpServletResponse response) throws IOException;


    /**
     * 查询数据分页
     * @param  user 条件参数
     * @param  status 条件参数
     * @param  StartTime 条件参数
     * @param  endTime 条件参数
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> feedbackList(Integer user,Integer status,String StartTime,String endTime, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param  user 条件参数
     * @param  status 条件参数
     * @param  StartTime 条件参数
     * @param  endTime 条件参数
     * @return List<ZbFeedbackDto>
     */
    List<ZbFeedback> feedbackList(Integer user,Integer status,String StartTime,String endTime);
}
