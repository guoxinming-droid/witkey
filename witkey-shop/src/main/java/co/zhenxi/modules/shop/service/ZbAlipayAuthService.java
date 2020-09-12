/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbAlipayAuth;
import co.zhenxi.modules.shop.service.dto.ZbAlipayAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbAlipayAuthQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author Gxm
* @date 2020-07-16
*/
public interface ZbAlipayAuthService  extends BaseService<ZbAlipayAuth>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbAlipayAuthQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbAlipayAuthDto>
    */
    List<ZbAlipayAuth> queryAll(ZbAlipayAuthQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbAlipayAuthDto> all, HttpServletResponse response) throws IOException;



    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> alipayAuthList(ZbAlipayAuthQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbAlipayAuthDto>
     */
    List<ZbAlipayAuth> alipayAuthList(ZbAlipayAuthQueryCriteria criteria);

    /**
     *支付宝审核
     * @param id
     * @param status
     */
    void onStatus(Integer id, int status);

    /**
     * 根据用户名获取
     * @param uid
     * @return
     */
    ZbAlipayAuth getByUid(Integer uid);
}
