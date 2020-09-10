package co.zhenxi.modules.shop.service;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbTaskServiceDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ZbTaskServiceMapper extends CoreMapper<ZbTaskServiceDomain> {
}
