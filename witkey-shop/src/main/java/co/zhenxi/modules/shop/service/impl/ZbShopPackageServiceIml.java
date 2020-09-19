package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.modules.shop.domain.*;
import co.zhenxi.modules.shop.service.*;
import co.zhenxi.modules.shop.service.mapper.ZbShopOrderMapper;
import co.zhenxi.modules.shop.service.mapper.ZbShopPackageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-15 16:57
 * @Description: ZbShopPackageServiceIml
 **/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbShopOrder")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbShopPackageServiceIml extends BaseServiceImpl<ZbShopPackageMapper, ZbShopPackage> implements ZbShopPackageService {

    private final ZbShopPackageMapper zbShopPackageMapper;

    private final ZbShopService zbShopService;

    private final ZbPrivilegesService zbPrivilegesService;

    private final ZbUsersService zbUsersService;

    private final ZbPackageService zbPackageService;

    /**
     * 插入数据店铺开通vip
     *
     * @param packageId
     * @param uid
     * @param time
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Integer packageId, Integer uid, Integer time) throws Exception {
        ZbShopPackage zbShopPackage = new ZbShopPackage();
        zbShopPackageMapper.insert(getZbShopPackage(packageId,uid,time));
    }

    /**
     * 插入数据店铺开通vip
     *
     * @param zbShopPackage
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(ZbVipshopOrder zbShopPackage) throws Exception {
        @NotNull Integer packageId = zbShopPackage.getPackageId();
        @NotNull Integer uid = zbShopPackage.getUid();
        @NotNull Integer timePeriod = zbShopPackage.getTimePeriod();
        getZbShopPackage(packageId,uid,timePeriod);
    }




    private ZbShopPackage getZbShopPackage(Integer packageId, Integer uid, Integer time)throws Exception{
        ZbShop zbShop = zbShopService.getshopByuid(uid);
        ZbShopPackage zbShopPackage = new ZbShopPackage();
        if(zbShop == null){
            throw new Exception();
        }
        zbShopPackage.setShopId(zbShop.getId());
        zbShopPackage.setPackageId(packageId);
        List<Integer> list = zbPrivilegesService.getPrivilegesIds(packageId);
        zbShopPackage.setPrivilegesPackage(list.toString());
        ZbUsers users = zbUsersService.getById(uid);
        if(users == null){
            throw new Exception();
        }
        zbShopPackage.setUid(uid);
        zbShopPackage.setUsername(users.getName());
        zbShopPackage.setDuration(time);
        ZbPackage zbPackage = zbPackageService.getById(packageId);
        if(zbPackage == null){
            throw new Exception();
        }
        BigDecimal price = new BigDecimal(zbPackage.getPrice()).multiply(new BigDecimal(time));
        zbShopPackage.setPrice(price);
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        zbShopPackage.setStartTime(createdTime);
        Date date = new Date();
        zbShopPackage.setEndTime(getThreeMth(date));
        zbShopPackage.setStatus(0);
        zbShopPackage.setCreatedAt(createdTime);
        zbShopPackage.setUpdatedAt(createdTime);

        return zbShopPackage;

    }

    private Timestamp getThreeMth(Date date){

//创建Calendar实例
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);   //设置当前时间
        //cal.add(Calendar.YEAR, 1);  //在当前时间基础上加一年

//将时间格式化成yyyy-MM-dd HH:mm:ss的格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(cal.getTime()));

//同理增加一个月和一天的方法：
        cal.add(Calendar.MONTH, 3);
         return new Timestamp(cal.getTimeInMillis());
    }
}
