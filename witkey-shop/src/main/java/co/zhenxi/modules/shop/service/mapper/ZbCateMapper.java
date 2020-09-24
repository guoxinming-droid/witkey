/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbCate;
import co.zhenxi.modules.shop.domain.ZbCateAdvice;
import co.zhenxi.modules.shop.service.dto.ZbCateQueryCriteria;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Guoxm
* @date 2020-07-20
*/
@Repository
@Mapper
public interface ZbCateMapper extends CoreMapper<ZbCate> {

    @Select("select * zb_cate where = #{fid}")
    List<ZbCate> getByFid(long fid);

    @Select("select id from zb_cate  where pid = 0")
    List<Integer> getByFida();

    @Select("SELECT a.id, a.created_at, a.path, a.name pname, b.name, a.sort, a.pic, a.choose_num, a.updated_at FROM zb_cate AS a  LEFT JOIN zb_cate b ON a.id = b.pid  where   a.pid = 0 order \n" +
            "by id DESC")
    List<ZbCate> queryAll(ZbCateQueryCriteria criteria);

    @Select("select * from zb_cate ${whereSql}")
    List<ZbCate> getZbCatesList(String whereSql);

    @Select("select zb1.* ,zb2.name pname from zb_cate zb1, zb_cate zb2 where zb1.id = #{siD} and zb1.pid=zb2.id")
    ZbCate getBySid(Integer siD);



    @Select("select * from zb_cate where pid = 0 ORDER BY sort")
    @Results(id = "ZbCateMap",value = {
            @Result(id = true, column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "pid",property = "pid"),
            @Result(column = "path",property = "path"),
            @Result(column = "pic",property = "pic"),
            @Result(column = "sort",property = "sort"),
            @Result(column = "choose_num",property = "chooseNum"),
            @Result(column = "created_at",property = "createdAt"),
            @Result(column = "updated_at",property = "updatedAt"),
            @Result(column = "id",property = "list",
                    many =@Many(select = "co.zhenxi.modules.shop.service.mapper.ZbCateMapper.getAllById"),
                    javaType = List.class
            )

    }
    )
    List<ZbCateAdvice> getAll();
    @Select("select * from zb_cate where pid = #{id} ORDER BY sort")
    List<ZbCateAdvice> getAllById(Integer id);

    @Select("select id from zb_cate where pid = #{id} ORDER BY sort")
    List<Integer> getAllBypId(Integer id);

}
