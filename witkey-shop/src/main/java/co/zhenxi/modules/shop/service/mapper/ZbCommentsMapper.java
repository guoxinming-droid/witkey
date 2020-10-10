/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbComments;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-07-23
*/
@Repository
@Mapper
public interface ZbCommentsMapper extends CoreMapper<ZbComments> {

    @Select("SELECT *,(SELECT title from zb_task where id = task_id)  AS title,(SELECT name from zb_users where id = to_uid)  AS toName,(SELECT name from zb_users where id = from_uid) AS fromName FROM zb_comments   ${ew.customSqlSegment}  "  )
    List<ZbComments> selectList1(@Param("ew") Wrapper<ZbComments> queryWrapper);


    @Select(" \tSELECT\n" +
            "\tc.id,\n" +
            "\tc.task_id,\n" +
            "\tc.from_uid,\n" +
            "\tc.to_uid,\n" +
            "\tc.comment,\n" +
            "\tc.comment_by,\n" +
            "\tc.speed_score,\n" +
            "\tc.quality_score,\n" +
            "\tc.attitude_score,\n" +
            "\tc.created_at,\n" +
            "\tc.type,\n" +
            "\tud.avatar\n" +
            "\t,(SELECT NAME FROM zb_users WHERE c.from_uid = id ) AS fromName\n" +
            "\t,(SELECT NAME FROM zb_users WHERE c.to_uid = id ) AS toName \n" +
            "  FROM\n" +
            "\tzb_comments AS c \n" +
            "\tLEFT JOIN zb_user_detail AS ud ON c.from_uid = ud.uid\n" +
            "  WHERE\n" +
            "\ttask_id = ${taskId}  ")
    List<ZbComments> getCommentsByTaskId(long taskId);

}
