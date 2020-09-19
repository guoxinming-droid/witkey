/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbQuestion;
import co.zhenxi.modules.shop.service.dto.ZbQuestionDto;
import co.zhenxi.modules.shop.service.dto.ZbQuestionQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-08-06
*/
public interface ZbQuestionService  extends BaseService<ZbQuestion>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbQuestionQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbQuestionDto>
    */
    List<ZbQuestion> queryAll(ZbQuestionQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbQuestionDto> all, HttpServletResponse response) throws IOException;


    /**
     * 查询数据分页
     * @param userName 条件
     * @param userName 条件
     * @param category 条件
     * @param status 条件
     * @param startTime 条件
     * @param endTime 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> getZbQuestionsList(String userName,Integer category,Integer status,String startTime,String endTime,Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param userName 条件
     * @param userName 条件
     * @param category 条件
     * @param status 条件
     * @param startTime 条件
     * @param endTime 条件
     * @return List<ZbQuestionDto>
     */
    List<ZbQuestion> getZbQuestionsList(String userName,Integer category,Integer status,String startTime,String endTime);


    /**
     * 审核
     * @param id
     * @param status
     */
    Boolean onStatus(Integer id, int status);

    Map<String,Object> getQuestion(Integer status,Pageable pageable);

    /**
     * 获取已解决得问题数量
     * @return
     */
    long getQuestionCount();
}
