/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.modules.shop.domain.ZbAlipayAuth;
import co.zhenxi.modules.shop.service.ZbAlipayAuthService;
import co.zhenxi.modules.shop.service.dto.ZbAlipayAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbAlipayAuthQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbAlipayAuthMapper;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author Gxm
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbAlipayAuth")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbAlipayAuthServiceImpl extends BaseServiceImpl<ZbAlipayAuthMapper, ZbAlipayAuth> implements ZbAlipayAuthService {

    private final IGenerator generator;
    private final ZbAlipayAuthMapper zbAlipayAuthMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbAlipayAuthQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbAlipayAuth> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbAlipayAuthDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbAlipayAuth> queryAll(ZbAlipayAuthQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbAlipayAuth.class, criteria));
    }


    @Override
    public void download(List<ZbAlipayAuthDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbAlipayAuthDto zbAlipayAuth : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户编号", zbAlipayAuth.getUid());
            map.put("用户名", zbAlipayAuth.getUsername());
            map.put("真实姓名", zbAlipayAuth.getRealname());
            map.put("支付宝姓名", zbAlipayAuth.getAlipayName());
            map.put("支付宝账户", zbAlipayAuth.getAlipayAccount());
            map.put("平台打款给用户的金额", zbAlipayAuth.getPayToUserCash());
            map.put("用户确认收到的金额", zbAlipayAuth.getUserGetCash());
            map.put("认证状态 ", zbAlipayAuth.getStatus());
            map.put("认证时间", zbAlipayAuth.getAuthTime());
            map.put("创建时间", zbAlipayAuth.getCreatedAt());
            map.put("修改时间", zbAlipayAuth.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> alipayAuthList(ZbAlipayAuthQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbAlipayAuth> page = new PageInfo<>(alipayAuthList(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbAlipayAuthDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbAlipayAuth> alipayAuthList(ZbAlipayAuthQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbAlipayAuth.class, criteria));
    }

    @Override
    public void onStatus(Integer id, int status) {
        zbAlipayAuthMapper.updateOnstatus(id,status);
    }

    /**
     * 根据用户名获取
     *
     * @param uid
     * @return
     */
    @Override
    public ZbAlipayAuth getByUid(Integer uid) {

        return zbAlipayAuthMapper.getByUid(uid);
    }
}
