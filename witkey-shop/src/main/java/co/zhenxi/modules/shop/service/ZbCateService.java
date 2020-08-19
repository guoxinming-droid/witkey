/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbCate;
import co.zhenxi.modules.shop.service.dto.ZbCateDto;
import co.zhenxi.modules.shop.service.dto.ZbCateQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author Guoxm
* @date 2020-07-20
*/
public interface ZbCateService  extends BaseService<ZbCate>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbCateQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbCateDto>
    */
    List<ZbCate> queryAll(ZbCateQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbCateDto> all, HttpServletResponse response) throws IOException;

    List<ZbCate> getByFid(long fid);


    List<ZbCate> getZbCatesList(Integer fid);
}
