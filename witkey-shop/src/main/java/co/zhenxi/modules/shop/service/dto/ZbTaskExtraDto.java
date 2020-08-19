/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author guoke
* @date 2020-07-22
*/
@Data
public class ZbTaskExtraDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 任务编号 */
    private Integer taskId;

    /** seo标题 */
    private String seoTitle;

    /** seo关键词 */
    private String seoKeyword;

    /** seo描述 */
    private String seoContent;

    /** 创建时间 */
    private Timestamp createdAt;

    /** 修改时间 */
    private Timestamp updatedAt;
}
