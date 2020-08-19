/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbUsers;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbUsersService;
import co.zhenxi.modules.shop.service.dto.ZbUsersDto;
import co.zhenxi.modules.shop.service.dto.ZbUsersQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbUsersMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author guoke
* @date 2020-07-22
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbUsers")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbUsersServiceImpl extends BaseServiceImpl<ZbUsersMapper, ZbUsers> implements ZbUsersService {

    private final IGenerator generator;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbUsersQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbUsers> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbUsersDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbUsers> queryAll(ZbUsersQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbUsers.class, criteria));
    }


    @Override
    public void download(List<ZbUsersDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbUsersDto zbUsers : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户名", zbUsers.getName());
            map.put("用户邮箱", zbUsers.getEmail());
            map.put("用户手机注册", zbUsers.getMobile());
            map.put("用户邮箱认证状态", zbUsers.getEmailStatus());
            map.put("用户密码", zbUsers.getPassword());
            map.put("支付密码", zbUsers.getAlternatePassword());
            map.put("随机码", zbUsers.getSalt());
            map.put("账户状态 ", zbUsers.getStatus());
            map.put("找回密码邮件过期时间", zbUsers.getOverdueDate());
            map.put("找回密码随机码", zbUsers.getValidationCode());
            map.put("重置密码邮件过期时间", zbUsers.getExpireDate());
            map.put("重置密码验证随机码", zbUsers.getResetPasswordCode());
            map.put("token", zbUsers.getRememberToken());
            map.put("最后一次登录时间", zbUsers.getLastLoginTime());
            map.put("注册来源", zbUsers.getSource());
            map.put("是否会员", zbUsers.getIsvip());
            map.put("创建时间", zbUsers.getCreatedAt());
            map.put("修改时间", zbUsers.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
