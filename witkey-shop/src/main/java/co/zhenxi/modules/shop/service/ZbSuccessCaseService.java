/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbSuccessCase;
import co.zhenxi.modules.shop.service.dto.ZbSuccessCaseDto;
import co.zhenxi.modules.shop.service.dto.ZbSuccessCaseQueryCriteria;
import com.github.pagehelper.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-07-30
*/
public interface ZbSuccessCaseService  extends BaseService<ZbSuccessCase>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbSuccessCaseQueryCriteria criteria, Pageable pageable);


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> successCaseList(ZbSuccessCaseQueryCriteria criteria, Pageable pageable);
    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<ZbSuccessCaseDto>
     */
    List<ZbSuccessCase> successCaseList(ZbSuccessCaseQueryCriteria criteria);
    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbSuccessCaseDto>
    */
    List<ZbSuccessCase> queryAll(ZbSuccessCaseQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbSuccessCaseDto> all, HttpServletResponse response) throws IOException;

    /**
     * 案例
     * @param uid
     * @return
     */
    List<ZbSuccessCase> getSuccessCaseyUId(Integer uid);

    /**
     * 获取推荐位的成功案例
     * @param size
     * @return
     */
    Map<String, Object> getSuccessStories(Pageable size);
}
