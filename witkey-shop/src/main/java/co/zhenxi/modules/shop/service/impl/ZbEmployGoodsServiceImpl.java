/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbEmployGoods;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.ValidationUtil;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbEmployGoodsService;
import co.zhenxi.modules.shop.service.dto.ZbEmployGoodsDto;
import co.zhenxi.modules.shop.service.dto.ZbEmployGoodsQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbEmployGoodsMapper;
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
* @date 2020-07-28
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbEmployGoods")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbEmployGoodsServiceImpl extends BaseServiceImpl<ZbEmployGoodsMapper, ZbEmployGoods> implements ZbEmployGoodsService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbEmployGoodsQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbEmployGoods> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbEmployGoodsDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbEmployGoods> queryAll(ZbEmployGoodsQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbEmployGoods.class, criteria));
    }


    @Override
    public void download(List<ZbEmployGoodsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbEmployGoodsDto zbEmployGoods : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("关联雇佣id", zbEmployGoods.getEmployId());
            map.put("关联服务id", zbEmployGoods.getServiceId());
            map.put("关联创建时间", zbEmployGoods.getCreatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
