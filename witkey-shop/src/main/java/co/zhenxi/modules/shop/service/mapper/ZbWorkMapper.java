/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbWork;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
* @author guoke
* @date 2020-07-23
*/
@Repository
@Mapper
public interface ZbWorkMapper extends CoreMapper<ZbWork> {

   @Select("SELECT DISTINCT\n" +
           "\tzb_user_detail.avatar,\n" +
           "\tzb_work.id AS workId, \n" +
           "\tzb_users.NAME AS username,\n" +
           "\tzb_work.created_at AS time,\n" +
           "\tCONCAT( ( zb_user_detail.receive_task_num / zb_user_detail.employee_praise_rate ) * 100, '%' ) AS goodComment,\n" +
           "CASE\n" +
           "\tzb_work.STATUS \n" +
           "\tWHEN 0 THEN\n" +
           "\t'投稿中' \n" +
           "\tWHEN 1 THEN\n" +
           "\t'中标' \n" +
           "\tWHEN 2 THEN\n" +
           "\t'交付' \n" +
           "\tWHEN 3 THEN\n" +
           "\t'验收成功' \n" +
           "END AS STATUS \n" +
           "FROM\n" +
           "\tzb_work,\n" +
           "\tzb_task,\n" +
           "\tzb_users,\n" +
           "\tzb_user_detail \n" +
           "WHERE\n" +
           "\tzb_work.task_id = #{taskId} \n" +
           "\tAND zb_task.id = zb_work.task_id \n" +
           "\tAND zb_work.uid = zb_users.id \n" +
           "\tAND zb_users.id = zb_user_detail.uid \n" +
           "\tAND zb_work.STATUS IN ( 0, 1, 2, 3 )")
   List<Map<String,Object>> getWorkByTaskId(long taskId);


    @Select("select * from zb_work where status = 2 ${orderSql} ")
    Page<ZbWork> getWorkAll(String orderSql);

    @Insert("<script>" +
            "insert into zb_work<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            " <if test=\"taskId != null\">" +
            "task_id," +
            "</if>" +
            "<if test=\"createdAt != null\">" +
            "created_at," +
            "</if>" +
            "<if test=\"uid != null\">" +
            "uid," +
            "</if>" +
            "<if test=\"price != null\">" +
            "price," +
            "</if>" +
            "<if test=\"forbidden != null\">" +
            " forbidden," +
            "</if>" +
            "<if test=\"status != null\">" +
            "status," +
            "</if>" +
            "<if test=\"descs != null\">" +
            "descs" +
            "</if>"+
            "</trim>" +
            "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">" +
            "<if test=\"taskId != null\">" +
            "#{taskId}," +
            "</if>\n" +
            "<if test=\"createdAt != null\">" +
            "#{createdAt}," +
            "</if>\n" +
            "<if test=\"uid != null\">" +
            "#{uid}," +
            "</if>\n" +
            "<if test=\"price != null\">" +
            "#{price}," +
            "</if>\n" +
            "<if test=\"forbidden != null\">" +
            "#{forbidden}," +
            "</if>" +
            "<if test=\"status != null\">" +
            "#{status}," +
            "</if>\n" +
            "<if test=\"descs != null\">" +
            "#{descs}" +
            "</if>" +
            "</trim>" +
            "</script>")
    void insera(ZbWork zbWork);

    @Select("select * from zb_work where task_id =#{id}")
    List<ZbWork> getWorkByTaskId1(Integer id);

    @Select("select id from zb_work where uid = #{uid} and task_id =#{taskId}")
    Integer getWorkByUidAndTaskId(Integer uid, Integer taskId);

    @Insert("insert into zb_work_attachment values(#{taskId},#{id},#{id1},#{suffix},#{timestamp})")
    void insertWorkAttachment(Integer taskId, Integer id, Long id1, String suffix, Timestamp timestamp);

    @Select("select attachment_id as localId,type from zb_work_attachment where work_id = #{workId} and task_id = #{id}")
    List<Map<String, Object>> getWorkAttachment(Integer workId, long id);
}
