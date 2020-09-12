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
import co.zhenxi.modules.shop.domain.ZbRealnameAuth;
import co.zhenxi.modules.shop.service.ZbRealnameAuthService;
import co.zhenxi.modules.shop.service.dto.ZbRealnameAuthDto;
import co.zhenxi.modules.shop.service.dto.ZbRealnameAuthQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbRealnameAuthMapper;
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
//@CacheConfig(cacheNames = "zbRealnameAuth")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbRealnameAuthServiceImpl extends BaseServiceImpl<ZbRealnameAuthMapper, ZbRealnameAuth> implements ZbRealnameAuthService {

    private final IGenerator generator;
    private final ZbRealnameAuthMapper zbRealnameAuthMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbRealnameAuthQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbRealnameAuth> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbRealnameAuthDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbRealnameAuth> queryAll(ZbRealnameAuthQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbRealnameAuth.class, criteria));
    }

    @Override
    public Map<String, Object> realnameAuthList(ZbRealnameAuthQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbRealnameAuth> page = new PageInfo<>(realnameAuthList(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbRealnameAuthDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbRealnameAuth> realnameAuthList(ZbRealnameAuthQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbRealnameAuth.class, criteria));
    }


    @Override
    public void download(List<ZbRealnameAuthDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbRealnameAuthDto zbRealnameAuth : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("关联用户id", zbRealnameAuth.getUid());
            map.put("用户名", zbRealnameAuth.getUsername());
            map.put("用户真实姓名", zbRealnameAuth.getRealname());
            map.put("用户证件号", zbRealnameAuth.getCardNumber());
            map.put("身份证正面", zbRealnameAuth.getCardFrontSide());
            map.put("身份证背面", zbRealnameAuth.getCardBackDside());
            map.put("持证验证图片", zbRealnameAuth.getValidationImg());
            map.put("认证状态 0：待验证 1：成功 2：失败", zbRealnameAuth.getStatus());
            map.put("证件类型  1-身份证  2-护照", zbRealnameAuth.getCardType());
            map.put("认证类型  1-身份认证  2-企业认证", zbRealnameAuth.getType());
            map.put("认证通过时间", zbRealnameAuth.getAuthTime());
            map.put(" createdAt",  zbRealnameAuth.getCreatedAt());
            map.put(" updatedAt",  zbRealnameAuth.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void onStatus(Integer id, int status) {
        if(status == 1){
            status = 1;
        }else{
            status = 2;
        }

        zbRealnameAuthMapper.updateOnstatus(status,id);
    }

    /**
     * 根据用户Id
     *
     * @param uid
     * @param i
     * @return
     */
    @Override
    public ZbRealnameAuth getByUid(Integer uid, int i) {

        return zbRealnameAuthMapper.getByUid(uid,i);
    }


}
