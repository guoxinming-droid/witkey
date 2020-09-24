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
public class ZbTaskQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String title;


    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String tagName;


    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String tagPname;

    /** 精确 */
    @Query
    private Integer typeId;

    /** 精确 */
    @Query
    private Integer cateId;

    /** 精确 */
    @Query
    private Integer catePid;

    /** 精确 */
    @Query
    private Integer status;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
