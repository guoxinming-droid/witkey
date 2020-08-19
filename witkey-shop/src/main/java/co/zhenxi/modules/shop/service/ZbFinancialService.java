/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbFinancial;
import co.zhenxi.modules.shop.service.dto.ZbFinancialDto;
import co.zhenxi.modules.shop.service.dto.ZbFinancialQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author Guoxm
* @date 2020-07-16
*/
public interface ZbFinancialService  extends BaseService<ZbFinancial>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbFinancialQueryCriteria criteria, Pageable pageable);


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> financeRecharge(ZbFinancialQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbFinancialDto>
    */
    List<ZbFinancial> queryAll(ZbFinancialQueryCriteria criteria);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbFinancialDto>
     */
    List<ZbFinancial> financeRecharge(ZbFinancialQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbFinancialDto> all, HttpServletResponse response) throws IOException;

    /**
     * 收支表图形报表
     * @param startTime
     * @param endTime
     * @return
     */
    List<ZbFinancial> financeStatement(String startTime,String endTime);

    /**
     *
     * @param
     * @return
     */
    Map<String,Object> rechargeList(String code ,String userName,String startTime,String endTime, Pageable pageable);

    /**
     *
     * @param
     * @return
     */
    List<ZbFinancial> rechargeList(String code ,String userName,String startTime,String endTime);

    /**
     *
     * @param action
     * @param startTime
     * @param endTime
     * @param pageable
     * @return
     */
    Map<String, Object> financeList(Integer action , String startTime, String endTime, Pageable pageable);

    /**
     *
     * @param whereSql
     * @return
     */
    List<ZbFinancial> financeList(String whereSql);


    Map<String, Object> userFinance(String userName ,Integer action,String startTime,String endTime, Pageable pageable);

    List<ZbFinancial> userFinance(String userName ,Integer action,String startTime,String endTime);
}
