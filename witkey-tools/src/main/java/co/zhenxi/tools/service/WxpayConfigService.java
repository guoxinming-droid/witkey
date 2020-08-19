/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.service;

import co.zhenxi.common.service.BaseService;
import co.zhenxi.tools.domain.WxpayConfig;
import co.zhenxi.tools.service.dto.WxpayConfigDto;
import co.zhenxi.tools.service.dto.WxpayConfigQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author Guo
* @date 2020-07-20
*/
public interface WxpayConfigService  extends BaseService<WxpayConfig>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(WxpayConfigQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<WxpayConfigDto>
    */
    List<WxpayConfig> queryAll(WxpayConfigQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<WxpayConfigDto> all, HttpServletResponse response) throws IOException;



    Map<String, Object> goWeChatPay(String orderId, HttpServletRequest request);


    String weChatNotify(String orderId,HttpServletRequest request);


}
