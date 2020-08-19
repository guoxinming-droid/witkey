/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbVipshopOrder;
import co.zhenxi.modules.shop.service.dto.ZbVipshopOrderDto;
import co.zhenxi.modules.shop.service.dto.ZbVipshopOrderQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author guoke
* @date 2020-07-27
*/
public interface ZbVipshopOrderService  extends BaseService<ZbVipshopOrder>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbVipshopOrderQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbVipshopOrderDto>
    */
    List<ZbVipshopOrder> queryAll(ZbVipshopOrderQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbVipshopOrderDto> all, HttpServletResponse response) throws IOException;
}
