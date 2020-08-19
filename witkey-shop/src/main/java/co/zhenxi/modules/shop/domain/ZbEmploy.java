/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.domain;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author guoke
* @date 2020-07-27
*/
@Data
@TableName("zb_employ")
public class ZbEmploy implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 雇佣标题 */
    @NotBlank
    private String title;


    /** 雇佣描述 */
    @NotBlank
    private String desc;


    /** 联系电话 */
    @NotBlank
    private String phone;


    /** 任务赏金 */
    @NotNull
    private BigDecimal bounty;


    /** 托管状态 */
    @NotNull
    private Integer bountyStatus;


    /** 截止时间 */
    @NotNull
    private Timestamp deliveryDeadline;


    /** 状态 */
    @NotNull
    private Integer status;


    /** 被雇佣人 */
    @NotNull
    private Integer employeeUid;


    /** 雇佣人 */
    @NotNull
    private Integer employerUid;


    /** 雇佣时间 */
    @NotNull
    private Timestamp employedAt;


    @NotNull
    private Integer employPercentage;


    private String seoTitle;


    private String seoKeywords;


    private String seoContent;


    /** 在此时间之后雇主就能够取消雇佣了 */
    private Timestamp cancelAt;


    /** 接受雇佣的最终时间限制 */
    private Timestamp exceptMaxAt;


    /** 结束时间 */
    private Timestamp endAt;


    /** 开始时间 */
    private Timestamp beginAt;


    /** 验收截止时间 */
    private Timestamp acceptDeadline;


    /** 验收时间 */
    private Timestamp acceptAt;


    /** 威客交付之后的维权期限 */
    private Timestamp rightAllowAt;


    /** 评价截止时间 */
    private Timestamp commentDeadline;


    /** 雇佣类型 */
    @NotNull
    private Integer employType;


    @NotNull
    private Timestamp createdAt;


    @NotNull
    private Timestamp updatedAt;


    public void copy(ZbEmploy source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
