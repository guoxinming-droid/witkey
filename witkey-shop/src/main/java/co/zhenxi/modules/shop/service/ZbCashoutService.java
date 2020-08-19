/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbCashout;
import co.zhenxi.modules.shop.service.dto.ZbCashoutDto;
import co.zhenxi.modules.shop.service.dto.ZbCashoutQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-08-01
*/
public interface ZbCashoutService  extends BaseService<ZbCashout>{

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbCashoutQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbCashoutDto>
    */
    List<ZbCashout> queryAll(ZbCashoutQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbCashoutDto> all, HttpServletResponse response) throws IOException;


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> financeWithdraw(ZbCashoutQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbCashoutDto>
     */
    List<ZbCashout> financeWithdraw(ZbCashoutQueryCriteria criteria);


    /**
     * 查询所有数据不分页
     * @param start 条件参数
     * @return List<ZbCashoutDto>
     */
    List<ZbCashout> financeProfit(String start, String end);

    /**
     *
     * @param cashoutType
     * @param userName
     * @param startTime
     * @param endTime
     * @param pageable
     * @return
     */
    Map<String, Object>  cashoutList(String cashoutType ,String userName,String startTime,String endTime, Pageable pageable);

    /**
     *
     * @param cashoutType
     * @param userName
     * @param startTime
     * @param endTime
     * @return
     */
    List<ZbCashout>  cashoutList(String cashoutType ,String userName,String startTime,String endTime);
}
