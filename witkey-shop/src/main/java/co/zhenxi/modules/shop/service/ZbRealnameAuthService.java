/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbRealnameAuth;
import co.zhenxi.modules.shop.service.dto.ZbRealnameAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbRealnameAuthQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-08-05
*/
public interface ZbRealnameAuthService  extends BaseService<ZbRealnameAuth>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbRealnameAuthQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbRealnameAuthDto>
    */
    List<ZbRealnameAuth> queryAll(ZbRealnameAuthQueryCriteria criteria);

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> realnameAuthList(ZbRealnameAuthQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbRealnameAuthDto>
     */
    List<ZbRealnameAuth> realnameAuthList(ZbRealnameAuthQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbRealnameAuthDto> all, HttpServletResponse response) throws IOException;

    /**
     *
     * @param id
     * @param status
     */
    void onStatus(Integer id, int status);

    /**
     * 根据用户Id
     * @param uid
     * @param i
     * @return
     */
    ZbRealnameAuth getByUid(Integer uid, int i);
}
