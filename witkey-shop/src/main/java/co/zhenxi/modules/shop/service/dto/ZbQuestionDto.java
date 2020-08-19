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
* @date 2020-08-06
*/
@Data
public class ZbQuestionDto implements Serializable {

    /** 问题列表自增id */
    private Integer id;

    /** 问题浏览次数 */
    private Integer num;

    /** 问题的描述 */
    private String discription;

    /** 问题是否解决 1表示发布 2表示审核通过 3表示已经回答 4表示问题解决 5表示审核失败 */
    private Integer status;

    /** 提问者uid */
    private Integer uid;

    /** 提问时间 */
    private Timestamp time;

    /** 审核时间 */
    private Timestamp verifyAt;

    /** 问题类别 */
    private Integer category;

    /** 问题被回答次数 */
    private Integer answernum;

    /** 点赞次数 */
    private Integer praisenum;
}
