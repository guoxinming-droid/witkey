/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbEmploy;
import co.zhenxi.modules.shop.domain.ZbTask;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author guoke
* @date 2020-07-27
*/
@Repository
@Mapper
public interface ZbEmployMapper extends CoreMapper<ZbEmploy> {

    @Select("SELECT\n" +
            "\te.*,\n" +
            "  eg.service_id,\t\n" +
            "\t(select name from zb_users where id = employer_uid) AS employerName,\n" +
            "\t(select name from zb_users where id = employee_uid) AS employeeName,\n" +
            "FROM\n" +
            "\tzb_employ_goods AS eg\n" +
            "\tLEFT JOIN zb_employ AS e ON eg.employ_id = e.id ${ew.customSqlSegment}")
    List<ZbTask> serviceOrderList(@Param("ew") Wrapper<ZbTask> queryWrapper);

}
