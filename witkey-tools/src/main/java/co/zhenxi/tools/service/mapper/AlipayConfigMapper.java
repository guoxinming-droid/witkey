/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.zhenxi.tools.service.mapper;

import co.zhenxi.tools.domain.AlipayConfig;
import co.zhenxi.common.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author hupeng
* @date 2020-05-13
*/
@Repository
@Mapper
public interface AlipayConfigMapper extends CoreMapper<AlipayConfig> {

}
