/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.modules.shop.domain.ZbCate;
import co.zhenxi.modules.shop.domain.ZbCateAdvice;
import co.zhenxi.modules.shop.service.ZbCateService;
import co.zhenxi.modules.shop.service.dto.ZbCateDto;
import co.zhenxi.modules.shop.service.dto.ZbCateQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbCateMapper;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
* @date 2020-07-20
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbCate")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbCateServiceImpl extends BaseServiceImpl<ZbCateMapper, ZbCate> implements ZbCateService {

    private final IGenerator generator;

    private final ZbCateMapper  zbCateMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbCateQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbCate> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbCateDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbCate> queryAll(ZbCateQueryCriteria criteria){
      //  return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbCate.class, criteria));
        return zbCateMapper.queryAll(criteria);
    }


    @Override
    public void download(List<ZbCateDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbCateDto zbCate : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("分类名称", zbCate.getName());
            map.put("父级分类", zbCate.getPid());
            map.put("分类路径", zbCate.getPath());
            map.put("分类图标", zbCate.getPic());
            map.put("排序", zbCate.getSort());
            map.put("点击量", zbCate.getChooseNum());
            map.put("创建时间", zbCate.getCreatedAt());
            map.put("修改时间", zbCate.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<ZbCate> getByFid(long fid) {
        return zbCateMapper.getByFid(fid);
    }

    @Override
    public List<ZbCate> getZbCatesList(Integer fid) {
        String whereSql = " where 1 = 1";
        if(fid != null && !"".equals(fid)){
            whereSql += " AND pid = "+fid;
        }else {
            whereSql += " AND pid = '0' ";
        }
        return zbCateMapper.getZbCatesList(whereSql);
    }

    @Override
    public List getAll() {

        return  zbCateMapper.getAll();
    }

    @Override
    public List<Integer> getByFida() {
        return zbCateMapper.getByFida();
    }

    /**
     * @param pid
     * @return
     */
    @Override
    public List<ZbCateAdvice> getCateType(Integer pid) {
        if(pid>=0){
            return  generator.convert(zbCateMapper.getAllById(pid), ZbCateAdvice.class);
        }
        return null;
    }
}
