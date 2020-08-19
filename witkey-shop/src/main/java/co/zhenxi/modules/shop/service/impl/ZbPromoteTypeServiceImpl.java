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
import co.zhenxi.modules.shop.domain.ZbPromoteType;
import co.zhenxi.modules.shop.service.ZbPromoteTypeService;
import co.zhenxi.modules.shop.service.dto.ZbPromoteTypeDto;
import co.zhenxi.modules.shop.service.dto.ZbPromoteTypeQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbPromoteTypeMapper;
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
* @date 2020-08-06
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbPromoteType")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbPromoteTypeServiceImpl extends BaseServiceImpl<ZbPromoteTypeMapper, ZbPromoteType> implements ZbPromoteTypeService {

    private final IGenerator generator;
    private final ZbPromoteTypeMapper zbPromoteTypeMapper;
    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbPromoteTypeQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbPromoteType> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbPromoteTypeDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbPromoteType> queryAll(ZbPromoteTypeQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbPromoteType.class, criteria));
    }


    @Override
    public void download(List<ZbPromoteTypeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbPromoteTypeDto zbPromoteType : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("推广类型名称", zbPromoteType.getName());
            map.put("名称代号 （拼音大写）", zbPromoteType.getCodeName());
            map.put("推广类型  1=>注册推广", zbPromoteType.getType());
            map.put("推广金额", zbPromoteType.getPrice());
            map.put("完成推广的条件  1=>实名认证  2=>邮箱认证 3=>支付认证", zbPromoteType.getFinishConditions());
            map.put("是否开启 1=>开启 2=>关闭", zbPromoteType.getIsOpen());
            map.put("创建时间", zbPromoteType.getCreatedAt());
            map.put("修改时间", zbPromoteType.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public ZbPromoteType promoteConfig(String codeName) {
        return zbPromoteTypeMapper.promoteConfig(codeName);
    }
}
