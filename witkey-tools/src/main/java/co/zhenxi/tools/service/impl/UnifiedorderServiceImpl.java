/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.service.impl;

import co.zhenxi.tools.domain.Unifiedorder;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.ValidationUtil;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.tools.service.UnifiedorderService;
import co.zhenxi.tools.service.dto.UnifiedorderDto;
import co.zhenxi.tools.service.dto.UnifiedorderQueryCriteria;
import co.zhenxi.tools.service.mapper.UnifiedorderMapper;
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
* @author Guoxm
* @date 2020-07-18
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "unifiedorder")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UnifiedorderServiceImpl extends BaseServiceImpl<UnifiedorderMapper, Unifiedorder> implements UnifiedorderService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(UnifiedorderQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<Unifiedorder> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), UnifiedorderDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<Unifiedorder> queryAll(UnifiedorderQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(Unifiedorder.class, criteria));
    }


    @Override
    public void download(List<UnifiedorderDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UnifiedorderDto unifiedorder : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" returnCode",  unifiedorder.getReturnCode());
            map.put(" returnMsg",  unifiedorder.getReturnMsg());
            map.put(" appid",  unifiedorder.getAppid());
            map.put(" mchId",  unifiedorder.getMchId());
            map.put(" deviceInfo",  unifiedorder.getDeviceInfo());
            map.put(" nonceStr",  unifiedorder.getNonceStr());
            map.put(" sign",  unifiedorder.getSign());
            map.put(" resultCode",  unifiedorder.getResultCode());
            map.put(" errCode",  unifiedorder.getErrCode());
            map.put(" errCodeDes",  unifiedorder.getErrCodeDes());
            map.put(" tradeType",  unifiedorder.getTradeType());
            map.put(" prepayId",  unifiedorder.getPrepayId());
            map.put(" body",  unifiedorder.getBody());
            map.put(" notifyUrl",  unifiedorder.getNotifyUrl());
            map.put(" outTradeNo",  unifiedorder.getOutTradeNo());
            map.put(" spbillCreateIp",  unifiedorder.getSpbillCreateIp());
            map.put(" totalFee",  unifiedorder.getTotalFee());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
