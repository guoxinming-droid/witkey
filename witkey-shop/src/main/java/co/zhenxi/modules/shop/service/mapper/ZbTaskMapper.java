/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbTask;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Repository
@Mapper
public interface ZbTaskMapper extends CoreMapper<ZbTask> {


    @Select("SELECT\n" +
            "\tta.id,\n" +
            "\tta.title,\n" +
            "\tu.username,\n" +
            "\tta.verified_at,\n" +
            "\tta.created_at,\n" +
            "\tta.STATUS,\n" +
            "\tta.bounty_status \n" +
            "FROM\n" +
            "\tzb_task AS ta\n" +
            "\tLEFT JOIN USER AS u ON ta.uid = u.id \n" +
            "\tLEFT JOIN zb_task_type  AS ztt ON ta.type_id = ztt.id\n" +
            "WHERE\n" +
            "\tztt.name = '悬赏模式'  ${ew.customSqlSegment} ")
    List<ZbTask> selectXSList(@Param("ew") Wrapper<ZbTask> queryWrapper);

    @Select("SELECT\n" +
            "\tta.id,\n" +
            "\tta.title,\n" +
            "\tu.username,\n" +
            "\tta.verified_at,\n" +
            "\tta.created_at,\n" +
            "\tta.STATUS,\n" +
            "\tta.bounty_status \n" +
            "FROM\n" +
            "\tzb_task AS ta\n" +
            "\tLEFT JOIN USER AS u ON ta.uid = u.id \n" +
            "\tLEFT JOIN zb_task_type  AS ztt ON ta.type_id = ztt.id\n" +
            "WHERE\n" +
            "\tztt.name = '招标模式' AND #{queryWrapper} ")
    List<ZbTask> selectZBList(@Param("ew") Wrapper<ZbTask> queryWrapper);


    @Select("SELECT a.id,a.uid,a.type_id, a.title, a.phone, a.bounty, a.created_at, a.status, a.task_desc FROM zb_task AS a  where a.id =${id}")
    ZbTask selectById1(@Param("id")long id);


    @Select(" select * from zb_task ${whereSql} ")
    List<ZbTask> getTaskHallList(String whereSql);




    @Select(" select * from zb_task  ${ew.customSqlSegment} ")
    List<ZbTask> getTaskList(@Param("ew") Wrapper<ZbTask> queryWrapper);

}
