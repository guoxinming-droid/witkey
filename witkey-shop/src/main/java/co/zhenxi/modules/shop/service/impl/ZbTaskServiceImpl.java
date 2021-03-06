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
import co.zhenxi.modules.shop.domain.ZbTaskAttachment;
import co.zhenxi.modules.shop.service.*;
import co.zhenxi.modules.shop.service.dto.ZbTaskDto;
import co.zhenxi.modules.shop.service.dto.ZbTaskQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbTaskMapper;
import co.zhenxi.modules.until.DateDown;
import co.zhenxi.tools.service.LocalStorageService;
import co.zhenxi.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import co.zhenxi.modules.system.service.UserService;

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
//@CacheConfig(cacheNames = "zbTask")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbTaskServiceImpl extends BaseServiceImpl<ZbTaskMapper, ZbTask> implements ZbTaskService {

    private final IGenerator generator;
    private final ZbTaskMapper  zbTaskMapper;
    private final ZbUsersService zbUserService;
    private final ZbTaskTypeService zbTaskTypeService;
    private final ZbTaskExtraService zbTaskExtraService;
    private final ZbTaskAttachmentService zbTaskAttachmentService;
    private final LocalStorageService localStorageService;
    private final ZbWorkService zbWorkService;
    private final ZbCommentsService zbCommentsService;
    private final ZbServiceService zbServiceService;

    @Override
    //@Cacheable
    public Map<String, Object> queryZBAll(ZbTaskQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTask> page = new PageInfo<>(queryZBAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbTask> queryZBAll(ZbTaskQueryCriteria criteria){
        List<ZbTask> zbTask =zbTaskMapper.selectZBList(QueryHelpPlus.getPredicate(ZbTask.class, criteria));
        return zbTask;
    }


    @Override
    //@Cacheable
    public Map<String, Object> queryXSAll(ZbTaskQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTask> page = new PageInfo<>(queryXSAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbTask> queryXSAll(ZbTaskQueryCriteria criteria){
        List<ZbTask> zbTask =zbTaskMapper.selectXSList(QueryHelpPlus.getPredicate(ZbTask.class, criteria));
        return zbTask;
    }

    @Override
    public void download(List<ZbTaskDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbTaskDto zbTask : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务标题", zbTask.getTitle());
            map.put("任务描述", zbTask.getTaskDesc());
            map.put("任务类型", zbTask.getTypeId());
            map.put("任务分类", zbTask.getCateId());
            map.put("联系电话", zbTask.getPhone());
            map.put("地域限制", zbTask.getRegionLimit());
            map.put("任务状态", zbTask.getStatus());
            map.put("赏金金额", zbTask.getBounty());
            map.put("赏金状态", zbTask.getBountyStatus());
            map.put("审核时间", zbTask.getVerifiedAt());
            map.put("任务开始时间", zbTask.getBeginAt());
            map.put("任务结束时间", zbTask.getEndAt());
            map.put("交稿结束时间", zbTask.getDeliveryDeadline());
            map.put("选稿时间", zbTask.getSelectedWorkAt());
            map.put("发布时间", zbTask.getPublicityAt());
            map.put("验收期进入时间", zbTask.getCheckedAt());
            map.put("双方互评开始时间", zbTask.getCommentAt());
            map.put("展示赏金", zbTask.getShowCash());
            map.put("实付赏金", zbTask.getRealCash());
            map.put("已托管金额", zbTask.getDepositCash());
            map.put("省", zbTask.getProvince());
            map.put("城市", zbTask.getCity());
            map.put("地区", zbTask.getArea());
            map.put("浏览次数", zbTask.getViewCount());
            map.put("投稿数量", zbTask.getDeliveryCount());
            map.put("用户编号", zbTask.getUid());
            map.put("用户名", zbTask.getUsername());
            map.put("服务商数量", zbTask.getWorkerNum());
            map.put("是否置顶", zbTask.getTopStatus());
            map.put("搜索引擎屏蔽", zbTask.getEngineStatus());
            map.put("稿件是否隐藏", zbTask.getWorkStatus());
            map.put("增值服务编号", zbTask.getService());
            map.put("成功抽成比率", zbTask.getTaskSuccessDrawRatio());
            map.put("失败抽成比率", zbTask.getTaskFailDrawRatio());
            map.put("交付状态", zbTask.getKeeStatus());
            map.put("来源链接", zbTask.getUrl());
            map.put("来源网站", zbTask.getSiteName());
            map.put("项目类型", zbTask.getType());
            map.put("二级标签", zbTask.getTagName());
            map.put("一级标签", zbTask.getTagPname());
            map.put("创建时间", zbTask.getCreatedAt());
            map.put("修改时间", zbTask.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public ZbTask getTasksById(long id) {
        ZbTask zbTask1 = zbTaskMapper.selectById(id);
        zbTask1.setZbUsers(zbUserService.getById(zbTask1.getUid()));
        zbTask1.setZbTaskType(zbTaskTypeService.getById(zbTask1.getTypeId()));
        zbTask1.setZbTaskExtra(zbTaskExtraService.getByTaskId(zbTask1.getId()));
        zbTask1.setZbComments(zbCommentsService.getCommentsByTaskId(zbTask1.getId()));
        ZbTaskAttachment  zbTaskAttachment = zbTaskAttachmentService.getByTaskId(zbTask1.getId());
        if(zbTaskAttachment!=null){
            zbTask1.setLocalStorage(localStorageService.getById(zbTaskAttachment.getAttachmentId()));
        }
        return generator.convert(zbTask1, ZbTask.class);
    }

    @Override
    public ZbTask getTasksWorkById(long id) {
        ZbTask zbTask1 = baseMapper.selectById(id);
        zbTask1.setZbWork(zbWorkService.getWorkByTaskId(zbTask1.getId()));
        return generator.convert(zbTask1, ZbTask.class);
    }

    @Override
    public Map<String, Object> getTaskList(ZbTaskQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbTask> page = new PageInfo<>(getTaskList(criteria));
        page.setSize(15);
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }

    @Override
    public List<ZbTask> getTaskList(ZbTaskQueryCriteria criteria) {
        List<ZbTask> zbTask =zbTaskMapper.getTaskList(QueryHelpPlus.getPredicate(ZbTask.class, criteria));

        //List<ZbTask> zbTask =zbTaskMapper.getTaskList(QueryHelpPlus.getPredicate(ZbTask.class, criteria));
        for(ZbTask task:zbTask){
            String dateDown = DateDown.CountDownDate(task.getCreatedAt(),new Timestamp(System.currentTimeMillis()));
            String countDown  = "距离时间："+ dateDown;
            task.setCountDown(countDown);
            task.setZbService(zbServiceService.getServiceListByTaskId(task.getId()));
        }
        return generator.convert(zbTask, ZbTask.class);
    }

    @Override
    public Map<String, Object> getTaskHallList(Integer typeId, Integer cateId, Pageable pageable) {
        getPage(pageable);
        System.out.println(pageable.getSort());
        List<ZbTask> pages = getTaskHallList(typeId,cateId);
//        for(ZbTask task:pages){
//            String  createTime =   DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss",task.getCreatedAt());
//            task.setCreatedAt(Timestamp.valueOf(createTime));
//        }
        PageInfo<ZbTask> page = new PageInfo<>(pages);
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbTaskDto.class));
        map.put("totalElements", page.isIsFirstPage());
        return map;
    }

    @Override
    public List<ZbTask> getTaskHallList(Integer typeId, Integer cateId) {
        String whereSql = " where 1 = 1 ";
        if(typeId !=null && !"".equals(typeId)){
            whereSql += " AND type_id = "+typeId;
        }
        if(cateId !=null && !"".equals(cateId)){
            whereSql += " AND cate_id = "+cateId;
        }
        whereSql += " AND  status in ('1','2','3','4','5','6','7','8','9','10','11') ";
        return zbTaskMapper.getTaskHallList(whereSql);
    }

}
