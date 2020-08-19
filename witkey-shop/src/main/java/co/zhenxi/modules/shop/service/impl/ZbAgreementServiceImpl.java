/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbAgreement;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbAgreementService;
import co.zhenxi.modules.shop.service.dto.ZbAgreementDto;
import co.zhenxi.modules.shop.service.dto.ZbAgreementQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbAgreementMapper;
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
* @author Gxm
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbAgreement")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbAgreementServiceImpl extends BaseServiceImpl<ZbAgreementMapper, ZbAgreement> implements ZbAgreementService {

    private final IGenerator generator;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbAgreementQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbAgreement> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbAgreementDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbAgreement> queryAll(ZbAgreementQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbAgreement.class, criteria));
    }


    @Override
    public void download(List<ZbAgreementDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbAgreementDto zbAgreement : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("协议名称", zbAgreement.getName());
            map.put("协议内容", zbAgreement.getContent());
            map.put("名称代号", zbAgreement.getCodeName());
            map.put("创建时间", zbAgreement.getCreatedAt());
            map.put("修改时间", zbAgreement.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
