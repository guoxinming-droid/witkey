/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbCate;
import co.zhenxi.modules.shop.service.dto.ZbCateQueryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

    @Select("SELECT a.id, a.created_at, a.path, a.name pname, b.name, a.sort, a.pic, a.choose_num, a.updated_at FROM zb_cate AS a  LEFT JOIN zb_cate b ON a.id = b.pid  where   a.pid = 0 order \n" +
            "by id DESC")
    List<ZbCate> queryAll(ZbCateQueryCriteria criteria);

    @Select("select * from zb_cate ${whereSql}")
    List<ZbCate> getZbCatesList(String whereSql);
}
