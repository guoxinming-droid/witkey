/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbSuccessCase;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-07-30
*/
@Repository
@Mapper
public interface ZbSuccessCaseMapper extends CoreMapper<ZbSuccessCase> {

    @Select({"SELECT\n" +
            "\tid,\n" +
            "\tuid,\n" +
            "\tusername,\n" +
            "\ttitle,\n" +
            "\turl,\n" +
            "\tpic,\n" +
            "\tcate_id AS cateId,\n" +
            "\t(select name FROM zb_cate where id = cate_id) AS cateName,\n" +
            "\tpub_uid AS pubUid,\n" +
            "\tview_count AS viewCount,\n" +
            "\tcreated_at AS createdAt,\n" +
            "\ttype,\n" +
            "\tdes \n" +
            "FROM\n" +
            "\tzb_success_case"})
    List<ZbSuccessCase> successCaseList(@Param("ew") Wrapper<ZbSuccessCase> queryWrapper);

    @Select(" SELECT  id,uid,username,title,des,url,pic,cate_id,type,pub_uid,view_count,created_at,cash FROM zb_success_case   WHERE pub_uid = ${uid} ")
    List<ZbSuccessCase> getSuccessCaseyUId(Integer uid);

}
