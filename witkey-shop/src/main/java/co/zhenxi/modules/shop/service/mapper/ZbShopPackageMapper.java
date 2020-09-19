package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbShopPackage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-15 16:53
 * @Description: ZbShopPackageMapper
 **/
@Repository
@Mapper
public interface ZbShopPackageMapper extends CoreMapper<ZbShopPackage> {
}
