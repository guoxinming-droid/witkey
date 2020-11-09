/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-07-28
*/
@Repository
@Mapper
public interface ZbServiceMapper extends CoreMapper<ZbService> {

    @Select(" SELECT  s.id,s.created_at,s.title,s.description,s.price,s.type,s.identify,s.status,s.created_at FROM zb_task_service AS ts  LEFT JOIN   zb_service AS s ON ts.  service_id  = s.id  WHERE s.status = '1' AND s.type = '1' AND ts.task_id = ${taskId} ")
    List<ZbService> getServiceListByTaskId(Integer taskId);

}
