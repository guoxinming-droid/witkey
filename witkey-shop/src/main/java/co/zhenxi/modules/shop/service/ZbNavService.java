package co.zhenxi.modules.shop.service;

import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbNav;

import java.util.List;

public interface ZbNavService extends BaseService<ZbNav> {
    /**
     *  查询全部
     * @return List<ZbNav>
     */
    List<ZbNav> queryAll();
}
