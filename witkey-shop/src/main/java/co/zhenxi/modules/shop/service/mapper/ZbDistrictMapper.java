package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbDistrict;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-28 16:31
 * @Description: ZbDistrictMapper
 **/
@Repository
@Mapper
public interface ZbDistrictMapper extends CoreMapper<ZbDistrict> {

    @Select("select * from zb_district where upid = 0 ORDER BY displayorder")
    @Results(id = "ZbDistrictMap",value = {
            @Result(id = true, column = "id",property = "id"),
            @Result(column = "upid",property = "uPid"),
            @Result(column = "name",property = "name"),
            @Result(column = "type",property = "type"),
            @Result(column = "displayorder",property = "disPlayOrder"),
            @Result(column = "spelling",property = "spelling"),
            @Result(column = "id",property = "list",
                    many =@Many(select = "co.zhenxi.modules.shop.service.mapper.ZbDistrictMapper.getAllById"),
                    javaType = List.class
            )

    }
    )
    List<ZbDistrict> getDistrict();

    @Select("select * from zb_district where upid = #{id} ORDER BY displayorder")
    @Results(id = "ZbDistrictMap1",value = {
            @Result(id = true, column = "id",property = "id"),
            @Result(column = "upid",property = "uPid"),
            @Result(column = "name",property = "name"),
            @Result(column = "type",property = "type"),
            @Result(column = "displayorder",property = "disPlayOrder"),
            @Result(column = "spelling",property = "spelling"),
            @Result(column = "id",property = "list",
                    many =@Many(select = "co.zhenxi.modules.shop.service.mapper.ZbDistrictMapper.getAllById"),
                    javaType = List.class
            )

    }
    )
    List<ZbDistrict> getAllById(Integer id);


    @Select("select * from zb_district where upid = #{id} ORDER BY displayorder")
    List<ZbDistrict> getAllById1(Integer id);

    @Select("select zb_district.name pName from zb_district where id = #{id} ")
    String getAllById2(Integer id);
    @Select("select *  from zb_district where upid = #{pid} and id<91137 ")
    Page<ZbDistrict> getAllByPid(Integer pid);

}
