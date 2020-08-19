/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbSetmoney;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbSetmoneyService;
import co.zhenxi.modules.shop.service.dto.ZbSetmoneyDto;
import co.zhenxi.modules.shop.service.dto.ZbSetmoneyQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbSetmoneyMapper;
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
* @author Guo
* @date 2020-07-20
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbSetmoney")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbSetmoneyServiceImpl extends BaseServiceImpl<ZbSetmoneyMapper, ZbSetmoney> implements ZbSetmoneyService {

    private final IGenerator generator;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbSetmoneyQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbSetmoney> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbSetmoneyDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbSetmoney> queryAll(ZbSetmoneyQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbSetmoney.class, criteria));
    }


    @Override
    public void download(List<ZbSetmoneyDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbSetmoneyDto zbSetmoney : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户最小充值金额", zbSetmoney.getRechargeMinAmount());
            map.put("用户最小提现金额", zbSetmoney.getWithdrawalMinAmount());
            map.put("用户当天提现最大金额", zbSetmoney.getDaywithdrawalMinAmount());
            map.put("单笔资费", zbSetmoney.getSingleCharge());
            map.put("单笔最低收费", zbSetmoney.getSingleMinimumCharge());
            map.put("单笔最高收费", zbSetmoney.getMaximumSingleCharge());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
