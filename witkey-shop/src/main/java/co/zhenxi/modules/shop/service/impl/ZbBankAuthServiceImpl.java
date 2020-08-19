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
import co.zhenxi.modules.shop.domain.ZbBankAuth;
import co.zhenxi.modules.shop.service.ZbBankAuthService;
import co.zhenxi.modules.shop.service.dto.ZbBankAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbBankAuthQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbBankAuthMapper;
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
* @author Guo xinming
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbBankAuth")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbBankAuthServiceImpl extends BaseServiceImpl<ZbBankAuthMapper, ZbBankAuth> implements ZbBankAuthService {

    private final IGenerator generator;
    private final ZbBankAuthMapper zbBankAuthMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbBankAuthQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbBankAuth> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbBankAuthDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbBankAuth> queryAll(ZbBankAuthQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbBankAuth.class, criteria));
    }


    @Override
    public void download(List<ZbBankAuthDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbBankAuthDto zbBankAuth : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户编号", zbBankAuth.getUid());
            map.put("用户名", zbBankAuth.getUsername());
            map.put("真实姓名", zbBankAuth.getRealname());
            map.put("银行名称", zbBankAuth.getBankName());
            map.put("图标", zbBankAuth.getBankImg());
            map.put("银行账号", zbBankAuth.getBankAccount());
            map.put("开户行地区", zbBankAuth.getDepositArea());
            map.put("开户行名称", zbBankAuth.getDepositName());
            map.put("打款金额", zbBankAuth.getPayToUserCash());
            map.put("收款金额", zbBankAuth.getUserGetCash());
            map.put("认证状态", zbBankAuth.getStatus());
            map.put("认证时间", zbBankAuth.getAuthTime());
            map.put("创建时间", zbBankAuth.getCreatedAt());
            map.put("修改时间", zbBankAuth.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> bankAuthList(ZbBankAuthQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbBankAuth> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbBankAuthDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbBankAuth> bankAuthList(ZbBankAuthQueryCriteria criteria) {
       return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbBankAuth.class, criteria));
    }

    @Override
    public void onStatus(Integer id, int status) {
        zbBankAuthMapper.updateOnstatus(id,status);
    }


}
