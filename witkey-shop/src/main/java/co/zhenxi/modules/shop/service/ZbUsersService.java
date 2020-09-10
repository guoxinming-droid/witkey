/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service;
import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbUsers;
import co.zhenxi.modules.shop.service.dto.ZbUsersDto;
import co.zhenxi.modules.shop.service.dto.ZbUsersQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author guoke
* @date 2020-07-22
*/
public interface ZbUsersService  extends BaseService<ZbUsers>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(ZbUsersQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ZbUsersDto>
    */
    List<ZbUsers> queryAll(ZbUsersQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ZbUsersDto> all, HttpServletResponse response) throws IOException;

    /**
     * 邮箱注册
     * @param zbUsers
     */
    ResponseEntity<Object> loginByEmail(ZbUsers zbUsers) throws Exception;

    /**
     * 手机注册
     * @param zbUsers
     */
    ZbUsers loginByPhoneNum(ZbUsers zbUsers);


    /**
     * 查看是不是数据库得邮箱
     * @param zbUsers
     */
    Map isEmail(ZbUsers zbUsers);


    /**
     * 看看用户是不是vip
     * @param uid
     * @return
     */
    ZbUsers isVIP(Integer uid);


    ZbUsers selectByMobile(String Mobile);

    /**
     *
     * @param id 用户id
     * @param password 更改的密码
     */
    void updatePassword(Integer id, String password);
}
