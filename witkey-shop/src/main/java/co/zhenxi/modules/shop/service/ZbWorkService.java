/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbWork;
import co.zhenxi.modules.shop.service.dto.ZbWorkDto;
import co.zhenxi.modules.shop.service.dto.ZbWorkQueryCriteria;
import co.zhenxi.tools.service.dto.LocalStorageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-07-23
*/
public interface ZbWorkService  extends BaseService<ZbWork>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbWorkQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbWorkDto>
    */
    List<ZbWork> queryAll(ZbWorkQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbWorkDto> all, HttpServletResponse response) throws IOException;

    List<Map<String,Object>> getWorkByTaskId(long taskId);

    /**
     * 获取中标信息表
     * @return
     */
    Map<String, Object> getWorkAll(Pageable size);

    /**
     * cha ru shu jv
     * @param zbWork
     * @param attachmentIds
     */
    Map insert(ZbWork zbWork, List<LocalStorageDto> attachmentIds);

    List<ZbWork> getWorkByTaskId1(Integer id);

    /**
     * 获取投稿附件Id 类型
     * @param workId
     * @param id
     * @return
     */
    List<Map<String, Object>> getWorkAttachment(Integer workId, long id);

    Map<String , Object> tenderWork(Integer taskId);
}
