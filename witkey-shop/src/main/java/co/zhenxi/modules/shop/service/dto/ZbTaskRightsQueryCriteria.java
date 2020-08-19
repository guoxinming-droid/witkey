/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;
import co.zhenxi.annotation.Query;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Data
public class ZbTaskRightsQueryCriteria{

    /** 精确 */
    @Query
    private Integer type;

    /** 精确 */
    @Query
    private Integer status;

    /** 精确 */
    @Query
    private Integer fromUid;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
