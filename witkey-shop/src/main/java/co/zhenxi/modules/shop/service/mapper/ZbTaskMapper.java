/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbTask;
import co.zhenxi.modules.shop.domain.ZbTaskAdvice;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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


    @Select("select zb_task.*,zb_cate.name cateName from zb_task ,zb_task_focus,zb_cate where zb_task.id = zb_task_focus.task_id and zb_task_focus.uid = #{uid} and zb_task.cate_id = zb_cate.id")
    List<ZbTaskAdvice> getCollectTask(@Param("uid")Integer uid);

    @Select("SELECT\n" +
            "\tzb_task.id id,\n" +
            "\tzb_task.title title,\n" +
            "\tzb_task.delivery_count deliveryCount,\n" +
            "\tzb_task.created_at time \n" +
            "FROM\n" +
            "\tzb_task \n" +
            "ORDER BY\n" +
            "\tcreated_at desc\n" +
            "\tLIMIT 0,\n" +
            "\t20")
    List<ZbTask> getByCreateTime(Timestamp createTime);


    @Insert("insert into zb_task_focus(task_id,uid,created_at,updated_at) value(#{taskId},#{uId},#{createAt},#{updateAt})")
    void insertCollectionTask(Integer taskId, Integer uId,Timestamp createAt,Timestamp updateAt);

    @Update("update zb_task set delivery_count = delivery_count+1 ,updated_at = #{timestamp} where id =#{taskId}")
    void updateDeliveryCount(Integer taskId, Timestamp timestamp);

    @Select("SELECT\n" +
            " zb_users.name as username,\n" +
            "\tzb_task.*,\n" +
            "CASE\n" +
            "\tzb_task.type_id \n" +
            "\tWHEN 1 THEN\n" +
            "\t'悬赏模式' \n" +
            "\tWHEN 3 THEN\n" +
            "\t'招标模式' \n" +
            "\tEND AS typeName \n" +
            "FROM\n" +
            "\tzb_task ,\n" +
            "\tzb_users\n" +
            "WHERE\n" +
            "\tzb_task.id = #{id}\n" +
            "\tand zb_task.uid = zb_users.id")
    ZbTaskAdvice getAllAndTaskTypeById(long id);


}
