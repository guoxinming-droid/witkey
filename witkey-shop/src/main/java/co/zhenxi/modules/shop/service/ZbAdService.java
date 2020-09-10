/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbAd;
import co.zhenxi.modules.shop.service.dto.ZbAdDto;
import co.zhenxi.modules.shop.service.dto.ZbAdQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Guoxm
* @date 2020-07-16
*/
public interface ZbAdService  extends BaseService<ZbAd>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbAdQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbAdDto>
    */
    List<ZbAd> queryAll(ZbAdQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbAdDto> all, HttpServletResponse response) throws IOException;

    /**
     * 查询指定广告位得广告
     * @param targetId 广告位ID
     * @param pageSize 广告个数
     * @return List
     */
    List queryAd(Integer targetId, Integer pageSize);
}
