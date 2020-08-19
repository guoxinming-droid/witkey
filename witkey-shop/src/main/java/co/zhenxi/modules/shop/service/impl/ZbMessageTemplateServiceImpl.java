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
import co.zhenxi.modules.shop.domain.ZbMessageTemplate;
import co.zhenxi.modules.shop.service.ZbMessageTemplateService;
import co.zhenxi.modules.shop.service.dto.ZbMessageTemplateDto;
import co.zhenxi.modules.shop.service.dto.ZbMessageTemplateQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbMessageTemplateMapper;
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
* @date 2020-08-05
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbMessageTemplate")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbMessageTemplateServiceImpl extends BaseServiceImpl<ZbMessageTemplateMapper, ZbMessageTemplate> implements ZbMessageTemplateService {

    private final IGenerator generator;
    private final ZbMessageTemplateMapper zbMessageTemplateMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbMessageTemplateQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbMessageTemplate> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbMessageTemplateDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbMessageTemplate> queryAll(ZbMessageTemplateQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbMessageTemplate.class, criteria));
    }


    @Override
    public void download(List<ZbMessageTemplateDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbMessageTemplateDto zbMessageTemplate : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("名称代号", zbMessageTemplate.getCodeName());
            map.put("信息邮件配置名称", zbMessageTemplate.getName());
            map.put("消息模版", zbMessageTemplate.getContent());
            map.put("类型 1->系统消息 2->交易动态", zbMessageTemplate.getMessageType());
            map.put("是否开启 1->是 2->否", zbMessageTemplate.getIsOpen());
            map.put("站内信息 1->是", zbMessageTemplate.getIsOnSite());
            map.put("发送邮件 1->是", zbMessageTemplate.getIsSendEmail());
            map.put("模板变量个数", zbMessageTemplate.getNum());
            map.put("变量名称 以逗号隔开", zbMessageTemplate.getVariableStr());
            map.put("发送短信 1->是", zbMessageTemplate.getIsSendMobile());
            map.put("短信模板编号", zbMessageTemplate.getCodeMobile());
            map.put("短信模板编号内容", zbMessageTemplate.getMobileCodeContent());
            map.put(" createdAt",  zbMessageTemplate.getCreatedAt());
            map.put(" updatedAt",  zbMessageTemplate.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Boolean changeStatus(Integer id, Integer isName, Integer status) {
        switch(isName) {
            case 1:
                Integer isOpen = status;
                return zbMessageTemplateMapper.changeIsOpen(id,isOpen);
            case 2:
                Integer isOnSite = status;
                return zbMessageTemplateMapper.changeIsOnSite(id,isOnSite);
            case 3:
                Integer isSendEmail = status;
                return zbMessageTemplateMapper.changeIsSendEmail(id,isSendEmail);
            case 4:
                Integer isSendMobile = status;
                return zbMessageTemplateMapper.changeIsSendMobile(id,isSendMobile);
        }
        return false;
    }
}
