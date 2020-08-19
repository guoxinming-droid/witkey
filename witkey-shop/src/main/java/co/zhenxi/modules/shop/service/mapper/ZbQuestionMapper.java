/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-08-06
*/
@Repository
@Mapper
public interface ZbQuestionMapper extends CoreMapper<ZbQuestion> {

    @Select("select q.id,q.num,q.discription,q.status,q.uid,q.time,q.verify_at,q.category,u.name AS userName from zb_question AS q LEFT JOIN zb_users AS u ON q.uid = u.id ${whereSql}")
    List<ZbQuestion> getZbQuestionsList(String whereSql);

    @Select(" UPDATE zb_question SET status =${status},verify_at = NOW() WHERE id = ${id} ")
    Boolean updateOnstatus(Integer id, int status);
}
