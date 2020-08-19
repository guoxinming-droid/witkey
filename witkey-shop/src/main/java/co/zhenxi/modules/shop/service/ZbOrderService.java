/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbOrder;
import co.zhenxi.modules.shop.service.dto.ZbOrderDto;
import co.zhenxi.modules.shop.service.dto.ZbOrderQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-08-01
*/
public interface ZbOrderService  extends BaseService<ZbOrder>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbOrderQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbOrderDto>
    */
    List<ZbOrder> queryAll(ZbOrderQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbOrderDto> all, HttpServletResponse response) throws IOException;



    /**
     * 查询数据分页
     * @param start 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> financeProfit(String start, String end,  String from, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param start 条件参数
     * @return List<ZbOrderDto>
     */
    List<ZbOrder> financeProfit1(String start,String end);

    /**
     * 收支表图形报表
     * @param startTime
     * @param endTime
     * @return
     */
    List<ZbOrder> financeStatement(String startTime, String endTime);


    /**
     *
     * @param code
     * @return
     */
    ZbOrder getByCode(String code);
}
