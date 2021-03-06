/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbTaskExtra;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
* @author guoke
* @date 2020-07-22
*/
@Repository
@Mapper
public interface ZbTaskExtraMapper extends CoreMapper<ZbTaskExtra> {

    @Select("select * from zb_task_extra where task_id = ${taskId}")
    ZbTaskExtra getByTaskId(long taskId);
}
