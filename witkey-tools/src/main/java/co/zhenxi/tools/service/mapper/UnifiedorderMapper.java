/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.tools.domain.Unifiedorder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author Guoxm
* @date 2020-07-18
*/
@Repository
@Mapper
public interface UnifiedorderMapper extends CoreMapper<Unifiedorder> {

}
