/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.zhenxi.tools.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.tools.domain.EmailConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author hupeng
* @date 2020-05-13
*/
@Repository
@Mapper
public interface EmailConfigMapper extends CoreMapper<EmailConfig> {

}
