package co.zhenxi.modules.shop.service;

import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbShopPackage;
import co.zhenxi.modules.shop.domain.ZbVipshopOrder;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-15 16:46
 * @Description: ZbShopPackageService
 **/
public interface ZbShopPackageService extends BaseService<ZbShopPackage> {
    /**
     * 插入数据店铺开通vip
     * @param packageId
     * @param uid
     * @param time
     */
    void insert(Integer packageId, Integer uid, Integer time) throws Exception;

    /**
     * 插入数据店铺开通vip
     * @param zbShopPackage
     *
     */
    void insert(ZbVipshopOrder zbShopPackage) throws Exception;
}
