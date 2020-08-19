/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbPromote;
import co.zhenxi.modules.shop.service.dto.ZbPromoteDto;
import co.zhenxi.modules.shop.service.dto.ZbPromoteQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-08-06
*/
public interface ZbPromoteService  extends BaseService<ZbPromote>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbPromoteQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbPromoteDto>
    */
    List<ZbPromote> queryAll(ZbPromoteQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbPromoteDto> all, HttpServletResponse response) throws IOException;


    /**
     * 查询数据分页
     * @param toName 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> promoteRelation(String toName,
                                       String fromName,
                                       String startTime,
                                       String endTime,
                                       Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param toName 条件参数
     * @return List<ZbPromoteDto>
     */
    List<ZbPromote> promoteRelation(String toName,
                                    String fromName,
                                    String startTime,
                                    String endTime);



    /**
     * 查询数据分页
     * @param toName 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> promoteFinance(String toName,
                                       String fromName,
                                       String startTime,
                                       String endTime,
                                       Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param toName 条件参数
     * @return List<ZbPromoteDto>
     */
    List<ZbPromote> promoteFinance(String toName,
                                    String fromName,
                                    String startTime,
                                    String endTime);





}
