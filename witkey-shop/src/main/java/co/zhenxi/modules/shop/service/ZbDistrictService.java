package co.zhenxi.modules.shop.service;

import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.ZbDistrict;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2020-08-28
 */
public interface ZbDistrictService extends BaseService<ZbDistrict> {

    List<ZbDistrict> getDistrict();

    Map<String,Object> getDistrictById(Integer pid, Integer size);
}
