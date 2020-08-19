/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbFastTask;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author Guo
* @date 2020-07-21
*/
@Repository
@Mapper
public interface ZbFastTaskMapper extends CoreMapper<ZbFastTask> {

//    @Select("select ta.id,ta.title,u.username,ta.verified_at,ta.created_at,ta.status,ta.bounty_status from zb_task AS  ta  LEFT JOIN user AS u ON ta.uid = u.id ")
//    List<ZbFastTask> selectList(Wrapper<ZbFastTask> queryWrapper);
}
