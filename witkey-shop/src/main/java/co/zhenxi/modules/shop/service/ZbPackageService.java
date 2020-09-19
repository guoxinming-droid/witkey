/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbPackage;
import co.zhenxi.modules.shop.service.dto.ZbPackageDto;
import co.zhenxi.modules.shop.service.dto.ZbPackageQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author Guoxm
* @date 2020-07-16
*/
public interface ZbPackageService  extends BaseService<ZbPackage>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbPackageQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbPackageDto>
    */
    List<ZbPackage> queryAll(ZbPackageQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbPackageDto> all, HttpServletResponse response) throws IOException;

    /**
     * 套餐上下架
     * @param id
     * @param status
     */
    void onStatus(Integer id, int status);

    /**
     * 查询全部不分页
     * @return
     */
    List<ZbPackage> getPackage();

}
