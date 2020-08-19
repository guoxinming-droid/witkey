/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbPrivileges;
import co.zhenxi.modules.shop.service.dto.ZbPrivilegesDto;
import co.zhenxi.modules.shop.service.dto.ZbPrivilegesQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author Guoxm
* @date 2020-07-16
*/
public interface ZbPrivilegesService  extends BaseService<ZbPrivileges>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbPrivilegesQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbPrivilegesDto>
    */
    List<ZbPrivileges> queryAll(ZbPrivilegesQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbPrivilegesDto> all, HttpServletResponse response) throws IOException;


    /**
     * 更改特权推荐状态
     * @param id
     * @param recommend
     */
    void isRecommend(Integer id, int recommend);


    /**
     * 更改特权启用状态
     * @param id
     * @param recommend
     */
    void onStatus(Integer id, int recommend);
}
