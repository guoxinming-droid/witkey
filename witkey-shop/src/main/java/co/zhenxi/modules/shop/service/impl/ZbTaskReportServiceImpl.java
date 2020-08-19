/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.modules.shop.domain.ZbTaskReport;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbTaskReportService;
import co.zhenxi.modules.shop.service.dto.ZbTaskReportDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskReportQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbTaskReportMapper;
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
* @author Guoxm
* @date 2020-07-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbTaskReport")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbTaskReportServiceImpl extends BaseServiceImpl<ZbTaskReportMapper, ZbTaskReport> implements ZbTaskReportService {

    private final IGenerator generator;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbTaskReportQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTaskReport> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskReportDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbTaskReport> queryAll(ZbTaskReportQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbTaskReport.class, criteria));
    }


    @Override
    public void download(List<ZbTaskReportDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbTaskReportDto zbTaskReport : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("举报类型", zbTaskReport.getType());
            map.put("举报的投稿", zbTaskReport.getTaskId());
            map.put("举报的投稿记录", zbTaskReport.getWorkId());
            map.put("举报描述", zbTaskReport.getDesc());
            map.put("状态", zbTaskReport.getStatus());
            map.put("举报人", zbTaskReport.getFromUid());
            map.put("被举报人", zbTaskReport.getToUid());
            map.put("附件", zbTaskReport.getAttachmentIds());
            map.put("处理方式 ", zbTaskReport.getHandleType());
            map.put("处理人", zbTaskReport.getHandleUid());
            map.put("创建时间", zbTaskReport.getCreatedAt());
            map.put("处理时间", zbTaskReport.getHandledAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
