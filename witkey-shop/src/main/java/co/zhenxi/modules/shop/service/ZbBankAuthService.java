/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbBankAuth;
import co.zhenxi.modules.shop.service.dto.ZbBankAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbBankAuthQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author Guo xinming
* @date 2020-07-16
*/
public interface ZbBankAuthService  extends BaseService<ZbBankAuth>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbBankAuthQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbBankAuthDto>
    */
    List<ZbBankAuth> queryAll(ZbBankAuthQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbBankAuthDto> all, HttpServletResponse response) throws IOException;




    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> bankAuthList(ZbBankAuthQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbBankAuthDto>
     */
    List<ZbBankAuth> bankAuthList(ZbBankAuthQueryCriteria criteria);


    /**
     *银行卡审核
     * @param id
     * @param status
     */
    void onStatus(Integer id, int status);

    /**
     * 根据用户ID
     * @param uid
     * @return
     */
    ZbBankAuth getByUid(Integer uid);

}
