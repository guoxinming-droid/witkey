/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbActivity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
* @author guoke
* @date 2020-07-31
*/
@Repository
@Mapper
public interface ZbActivityMapper extends CoreMapper<ZbActivity> {

    @Select("select * from zb_activity where status = 0 order by pub_at desc")
    Page<ZbActivity> getActivity();

}
