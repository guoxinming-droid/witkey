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


}
