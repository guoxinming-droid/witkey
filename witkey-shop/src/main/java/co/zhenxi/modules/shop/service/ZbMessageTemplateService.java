/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbMessageTemplate;
import co.zhenxi.modules.shop.service.dto.ZbMessageTemplateDto;
import co.zhenxi.modules.shop.service.dto.ZbMessageTemplateQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-08-05
*/
public interface ZbMessageTemplateService  extends BaseService<ZbMessageTemplate>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbMessageTemplateQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbMessageTemplateDto>
    */
    List<ZbMessageTemplate> queryAll(ZbMessageTemplateQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbMessageTemplateDto> all, HttpServletResponse response) throws IOException;


    /**
     *
     * @param id
     * @param isName
     * @param status
     * @return
     */
    Boolean changeStatus(@RequestParam(name="id") Integer id, @RequestParam(name="isName") Integer isName, @RequestParam(name="status") Integer status);
}
