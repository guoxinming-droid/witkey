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

import java.util.List;

/**
* @author guoke
* @date 2020-07-23
*/
@Repository
@Mapper
public interface ZbWorkMapper extends CoreMapper<ZbWork> {

   @Select("select * from zb_work where task_id = ${taskId}")
   List<ZbWork> getWorkByTaskId(long taskId);


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
}
