/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.tools.domain.Ordertest;
import co.zhenxi.tools.service.dto.OrdertestDto;
import co.zhenxi.tools.service.dto.OrdertestQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author gxm
* @date 2020-07-18
*/
public interface OrdertestService  extends BaseService<Ordertest>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(OrdertestQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<OrdertestDto>
    */
    List<Ordertest> queryAll(OrdertestQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<OrdertestDto> all, HttpServletResponse response) throws IOException;


    Ordertest selectByOrderId(String userId);
}
