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
import co.zhenxi.modules.shop.domain.ZbTask;
import co.zhenxi.modules.shop.domain.ZbUsers;
import co.zhenxi.modules.shop.domain.ZbWork;
import co.zhenxi.modules.shop.service.ZbUsersService;
import co.zhenxi.modules.shop.service.ZbWorkService;
import co.zhenxi.modules.shop.service.dto.ZbWorkDto;
import co.zhenxi.modules.shop.service.dto.ZbWorkQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbTaskMapper;
import co.zhenxi.modules.shop.service.mapper.ZbWorkMapper;
import co.zhenxi.tools.service.dto.LocalStorageDto;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.*;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author guoke
* @date 2020-07-23
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbWork")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbWorkServiceImpl extends BaseServiceImpl<ZbWorkMapper, ZbWork> implements ZbWorkService {

    private final IGenerator generator;
    private final ZbWorkMapper zbWorkMapper;
    private final ZbTaskMapper zbTaskMapper;
    private final ZbUsersService zbUsersService;


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbWorkQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbWork> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbWorkDto.class));
        map.put("pageNum",page.getPageNum());
        map.put("pages",page.getPages());
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbWork> queryAll(ZbWorkQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbWork.class, criteria));
    }


    @Override
    public void download(List<ZbWorkDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbWorkDto zbWork : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务ID", zbWork.getTaskId());
            map.put("被关注者id", zbWork.getDesc());
            map.put("状态", zbWork.getStatus());
            map.put("是否禁用稿件", zbWork.getForbidden());
            map.put("威客人员", zbWork.getUid());
            map.put("中标选中对象", zbWork.getBidBy());
            map.put("中标时间", zbWork.getBidAt());
            map.put("稿件创建时间", zbWork.getCreatedAt());
            map.put("威客报价金额", zbWork.getPrice());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<Map<String,Object>> getWorkByTaskId(long taskId) {

        return zbWorkMapper.getWorkByTaskId(taskId);
    }

    /**
     * 获取中标信息表
     *
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> getWorkAll(Pageable size) {
        getPage(size);
        String orderSql = "";
        if(size.getSort() == null){
            orderSql = "order by bid_at desc ";
        }
        //PageHelper.startPage(1,size);
        Page<ZbWork> page = zbWorkMapper.getWorkAll(orderSql);
        List<ZbWork> list = page.getResult();
        for (ZbWork zbWork : list) {
            zbWork.setPrice(zbWork.getPrice().setScale(2, RoundingMode.HALF_UP));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("content", generator.convert(list, ZbWork.class));
        map.put("pageNum",page.getPageNum());
        map.put("pages",page.getPages());
        map.put("totalElements", page.getTotal());


        return map;
    }

    /**
     * cha ru shu jv
     *  @param zbWork
     * @param attachmentIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map insert(ZbWork zbWork, List<LocalStorageDto> attachmentIds) {
        zbWork.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        HashMap<String, Object> map = new HashMap<>(2);
        ZbUsers vip = zbUsersService.isVIP(zbWork.getUid());
        //不是vip不让投稿一定金额的任务
        if(vip.getIsvip()!=1){
            map.put("flag",false);
            map.put("message","您无法投搞此金额的任务，可以购买vip增加可投稿金额");
            return map;
        }
        //判断是否投过了  不允许重复投递同一任务
        Integer workId = zbWorkMapper.getWorkByUidAndTaskId(zbWork.getUid(),zbWork.getTaskId());
        if(workId == null || workId <=0){
            map.put("flag",false);
            map.put("message","您已经投过稿了，换个任务试一下吧");
            return map;
        }
        zbWorkMapper.insera(zbWork);
        //投稿记录与附件
        for (LocalStorageDto localStorageDto : attachmentIds) {
            zbWorkMapper.insertWorkAttachment(zbWork.getTaskId(),zbWork.getId(),localStorageDto.getId(),localStorageDto.getSuffix(),new Timestamp(System.currentTimeMillis()));
        }
        zbTaskMapper.updateDeliveryCount(zbWork.getTaskId(),new Timestamp(System.currentTimeMillis()));
        map.put("flag",true);
        map.put("message","投稿成功");
        return map;
    }

    @Override
    public List<ZbWork> getWorkByTaskId1(Integer id) {

        return zbWorkMapper.getWorkByTaskId1(id);
    }

    /**
     * 获取投稿附件Id 类型
     *
     * @param workId
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> getWorkAttachment(Integer workId, long id) {

        return zbWorkMapper.getWorkAttachment(workId,id);
    }

    @Override
    public Map<String, Object> tenderWork(Integer taskId) {
        ZbTask zbTask = zbTaskMapper.selectById(taskId);
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("title",zbTask.getTitle());
        return linkedHashMap;
    }
}
