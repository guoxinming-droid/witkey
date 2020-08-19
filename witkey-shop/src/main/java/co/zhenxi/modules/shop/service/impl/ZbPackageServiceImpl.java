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
import co.zhenxi.modules.shop.domain.ZbPackage;
import co.zhenxi.modules.shop.service.ZbPackageService;
import co.zhenxi.modules.shop.service.dto.ZbPackageDto;
import co.zhenxi.modules.shop.service.dto.ZbPackageQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbPackageMapper;
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
* @author Guoxm
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbPackage")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbPackageServiceImpl extends BaseServiceImpl<ZbPackageMapper, ZbPackage> implements ZbPackageService {

    private final IGenerator generator;
    private final ZbPackageMapper zbPackageMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbPackageQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbPackage> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbPackageDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbPackage> queryAll(ZbPackageQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbPackage.class, criteria));
    }


    @Override
    public void download(List<ZbPackageDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbPackageDto zbPackage : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("套餐名称", zbPackage.getTitle());
            map.put("套餐logo", zbPackage.getLogo());
            map.put("套餐状态", zbPackage.getStatus());
            map.put("价格配置规则", zbPackage.getPriceRules());
            map.put("排序", zbPackage.getList());
            map.put("套餐类型", zbPackage.getType());
            map.put("套餐状态", zbPackage.getTypeStatus());
            map.put("创建时间", zbPackage.getCreatedAt());
            map.put("修改时间", zbPackage.getUpdatedAt());
            map.put("删除时间", zbPackage.getDeletedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onStatus(Integer id, int status) {
        if(status == 1){
            status = 1;
        }else{
            status = 0;
        }

        zbPackageMapper.updateOnstatus(status,id);
    }
}
