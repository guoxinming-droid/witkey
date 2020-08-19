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
import co.zhenxi.modules.shop.domain.ZbEnterpriseAuth;
import co.zhenxi.modules.shop.service.ZbEnterpriseAuthService;
import co.zhenxi.modules.shop.service.dto.ZbEnterpriseAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbEnterpriseAuthQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbEnterpriseAuthMapper;
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
* @author guoke
* @date 2020-08-05
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbEnterpriseAuth")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbEnterpriseAuthServiceImpl extends BaseServiceImpl<ZbEnterpriseAuthMapper, ZbEnterpriseAuth> implements ZbEnterpriseAuthService {

    private final IGenerator generator;
    private final ZbEnterpriseAuthMapper zbEnterpriseAuthMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbEnterpriseAuthQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbEnterpriseAuth> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbEnterpriseAuthDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbEnterpriseAuth> queryAll(ZbEnterpriseAuthQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbEnterpriseAuth.class, criteria));
    }


    @Override
    public void download(List<ZbEnterpriseAuthDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbEnterpriseAuthDto zbEnterpriseAuth : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户编号", zbEnterpriseAuth.getUid());
            map.put("公司名称", zbEnterpriseAuth.getCompanyName());
            map.put("行业末级分类", zbEnterpriseAuth.getCateId());
            map.put("员工人数", zbEnterpriseAuth.getEmployeeNum());
            map.put("营业执照", zbEnterpriseAuth.getBusinessLicense());
            map.put("开始经营时间", zbEnterpriseAuth.getBeginAt());
            map.put("公司网址", zbEnterpriseAuth.getWebsite());
            map.put("省", zbEnterpriseAuth.getProvince());
            map.put("市", zbEnterpriseAuth.getCity());
            map.put("区", zbEnterpriseAuth.getArea());
            map.put("公司详细地址", zbEnterpriseAuth.getAddress());
            map.put("认证状态 0：待验证 1：成功 2：失败", zbEnterpriseAuth.getStatus());
            map.put("认证时间", zbEnterpriseAuth.getAuthTime());
            map.put(" createdAt",  zbEnterpriseAuth.getCreatedAt());
            map.put(" updatedAt",  zbEnterpriseAuth.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> enterpriseAuthList(ZbEnterpriseAuthQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbEnterpriseAuth> page = new PageInfo<>(enterpriseAuthList(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbEnterpriseAuthDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbEnterpriseAuth> enterpriseAuthList(ZbEnterpriseAuthQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbEnterpriseAuth.class, criteria));
    }

    @Override
    public void onStatus(Integer id, int status) {
        zbEnterpriseAuthMapper.updateOnstatus(id,status);
    }
}
