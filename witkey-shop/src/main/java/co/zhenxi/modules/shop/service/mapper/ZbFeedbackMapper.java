/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbFeedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Guo xinming
* @date 2020-07-16
*/
@Repository
@Mapper
public interface ZbFeedbackMapper extends CoreMapper<ZbFeedback> {

    @Select(" select * from zb_feedback  ${whereSql} order by created_time DESC")
    List<ZbFeedback> feedbackList(String whereSql);
}
