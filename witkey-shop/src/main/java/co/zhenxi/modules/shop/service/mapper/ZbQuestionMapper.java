/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbQuestion;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Select("SELECT\n" +
            "\tzb_question.id,\n" +
            "\tzb_cate.NAME,\n" +
            "\tzb_question.discription,\n" +
            "CASE\n" +
            "\tzb_question.STATUS \n" +
            "\tWHEN 4 THEN\n" +
            "\t\"已解决\" ELSE \"待解决\" \n" +
            "\tEND AS STATUS,\n" +
            "\tzb_question.time\n" +
            "FROM\n" +
            "\tzb_cate,\n" +
            "\tzb_question \n" +
            "WHERE\n" +
            "\tzb_question.category = zb_cate.id ${whereSql}")
    Page<Map<String, Object>> getQuestion(String whereSql);


    @Select("SELECT ifnull(count( zb_answer.id ),0) FROM zb_question, zb_answer WHERE zb_question.id = zb_answer.questionid and zb_question.id=#{questionId}")
    Long getQuestionAnswer(Long questionId);

    @Select("select count(1) from zb_question where status = 4")
    long getQuestionCount();
}
