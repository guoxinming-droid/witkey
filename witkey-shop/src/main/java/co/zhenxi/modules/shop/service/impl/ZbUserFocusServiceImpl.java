/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbUserFocus;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.ValidationUtil;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbUserFocusService;
import co.zhenxi.modules.shop.service.dto.ZbUserFocusDto;
import co.zhenxi.modules.shop.service.dto.ZbUserFocusQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbUserFocusMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author guoke
* @date 2020-08-19
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbUserFocus")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbUserFocusServiceImpl extends BaseServiceImpl<ZbUserFocusMapper, ZbUserFocus> implements ZbUserFocusService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbUserFocusQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbUserFocus> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbUserFocusDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbUserFocus> queryAll(ZbUserFocusQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbUserFocus.class, criteria));
    }


    @Override
    public void download(List<ZbUserFocusDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbUserFocusDto zbUserFocus : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户id", zbUserFocus.getUid());
            map.put("被关注者id", zbUserFocus.getFocusUid());
            map.put(" createdAt",  zbUserFocus.getCreatedAt());
            map.put(" updatedAt",  zbUserFocus.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
