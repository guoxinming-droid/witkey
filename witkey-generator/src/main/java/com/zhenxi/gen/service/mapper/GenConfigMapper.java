/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.zhenxi.gen.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.gen.domain.GenConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GenConfigMapper extends CoreMapper<GenConfig> {
}
