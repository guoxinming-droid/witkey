/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbEmployWork;
import co.zhenxi.modules.shop.service.dto.ZbEmployWorkDto;
import co.zhenxi.modules.shop.service.dto.ZbEmployWorkQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author guoke
* @date 2020-08-21
*/
public interface ZbEmployWorkService  extends BaseService<ZbEmployWork>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbEmployWorkQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbEmployWorkDto>
    */
    List<ZbEmployWork> queryAll(ZbEmployWorkQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbEmployWorkDto> all, HttpServletResponse response) throws IOException;
}
