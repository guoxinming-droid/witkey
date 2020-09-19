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
import co.zhenxi.modules.shop.domain.ZbPackAdvice;
import co.zhenxi.modules.shop.domain.ZbPackage;
import co.zhenxi.modules.shop.domain.ZbPrivileges;
import co.zhenxi.modules.shop.rest.ZbAlipayAuthController;
import co.zhenxi.modules.shop.service.ZbPrivilegesService;
import co.zhenxi.modules.shop.service.dto.ZbPrivilegesDto;
import co.zhenxi.modules.shop.service.dto.ZbPrivilegesQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbPackageMapper;
import co.zhenxi.modules.shop.service.mapper.ZbPrivilegesMapper;
import co.zhenxi.modules.until.LineToHumpUtil;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//@CacheConfig(cacheNames = "zbPrivileges")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbPrivilegesServiceImpl extends BaseServiceImpl<ZbPrivilegesMapper, ZbPrivileges> implements ZbPrivilegesService {

    private final IGenerator generator;
    private final ZbPrivilegesMapper zbPrivilegesMapper;

    private final ZbPackageMapper zbPackageMapper;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbPrivilegesQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbPrivileges> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbPrivilegesDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbPrivileges> queryAll(ZbPrivilegesQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbPrivileges.class, criteria));
    }


    @Override
    public void download(List<ZbPrivilegesDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbPrivilegesDto zbPrivileges : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("特权名称", zbPrivileges.getTitle());
            map.put("特权描述", zbPrivileges.getDesc());
            map.put("特权编码", zbPrivileges.getCode());
            map.put("排序", zbPrivileges.getList());
            map.put("类型", zbPrivileges.getType());
            map.put("状态", zbPrivileges.getStatus());
            map.put("推荐状态", zbPrivileges.getIsRecommend());
            map.put("特权图标", zbPrivileges.getIco());
            map.put("创建时间", zbPrivileges.getCreatedAt());
            map.put("修改时间", zbPrivileges.getUpdatedAt());
            map.put("删除时间", zbPrivileges.getDeletedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void isRecommend(Integer id, int recommend) {
        if (recommend == 1) {
            recommend = 1;
        } else {
            recommend = 0;
        }

        zbPrivilegesMapper.updateIsRecommend(recommend, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onStatus(Integer id, int status) {
        if(status == 1){
            status = 1;
        }else{
            status = 0;
        }

        zbPrivilegesMapper.updateOnstatus(status,id);
    }

    /**
     * 获取特权信息
     *
     * @return
     */
    @Override
    public List<ZbPrivileges> getPrivileges() {
        return zbPrivilegesMapper.getPrivileges();
    }

    /**
     * 获取特权详情
     *
     * @return
     */
    @Override
    public List<ZbPackAdvice> getVipInfo() {

        return null;
    }

    /**
     * 获取特权服务的ID集合
     *
     * @param packageId
     * @return
     */
    @Override
    public List<Integer> getPrivilegesIds(Integer packageId) {

        return  zbPrivilegesMapper.getPrivilegesIds(packageId);
    }

    /**
     * 获取特权详情2
     *
     * @return
     */
    @Override
    public List<Map<String,Object>> getVipInfoT() {


        List<ZbPackage> vipInfoT = zbPackageMapper.getVipInfoT();
        List<ZbPrivileges> privilegesT = zbPrivilegesMapper.getPrivilegesT();
        ArrayList<Map<String,Object>> maps = new ArrayList<>();
        for (ZbPackage zbPackage : vipInfoT) {
            Map<String,Object> map = new LinkedHashMap<>(10);
            map.put("packageId",zbPackage.getId());
            map.put("packageTitle",zbPackage.getTitle());
            map.put("packagePrice",zbPackage.getPrice());
            for (ZbPrivileges zbPrivileges : privilegesT) {
                String code = LineToHumpUtil.lineToHump(zbPrivileges.getCode());
                System.out.println(code);
                map.put(code+"Ico",zbPrivileges.getIco());
                map.put(code+"Name",zbPrivileges.getTitle());
                map.put(code+"Rule", zbPrivilegesMapper.getPrivilegesIdRule(zbPackage.getId(), zbPrivileges.getId()));

            }
            maps.add(map);
        }

        return maps;
    }



    /**
     * 套餐详情
     *
     * @param packageId
     * @return
     */
    @Override
    public ZbPackAdvice getVipInfoDetails(Integer packageId) {

        return zbPrivilegesMapper.getVipInfo(packageId);
    }
}
