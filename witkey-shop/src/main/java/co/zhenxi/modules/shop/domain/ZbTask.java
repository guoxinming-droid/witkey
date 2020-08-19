/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.zhenxi.tools.domain.LocalStorage;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
* @author Guoxm
* @date 2020-07-16
*/
@Data
@TableName("zb_task")
public class ZbTask implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 任务标题 */
    @NotBlank
    private String title;


    /** 任务描述 */
    private String taskDesc;


    /** 任务类型 */
    @NotNull
    private Integer typeId;


    /** 任务分类 */
    @NotNull
    private Integer cateId;


    /** 联系电话 */
    @NotBlank
    private String phone;


    /** 地域限制 */
    @NotNull
    private Integer regionLimit;


    /** 任务状态 */
    @NotNull
    private Integer status;


    /** 赏金金额 */
    @NotNull
    private BigDecimal bounty;


    /** 赏金状态 */
    @NotNull
    private Integer bountyStatus;


    /** 审核时间 */
    private Timestamp verifiedAt;


    /** 任务开始时间 */
    private Timestamp beginAt;


    /** 任务结束时间 */
    private Timestamp endAt;


    /** 交稿结束时间 */
    private Timestamp deliveryDeadline;


    /** 选稿时间 */
    private Timestamp selectedWorkAt;


    /** 发布时间 */
    private Timestamp publicityAt;


    /** 验收期进入时间 */
    private Timestamp checkedAt;


    /** 双方互评开始时间 */
    private Timestamp commentAt;


    /** 展示赏金 */
    @NotNull
    private BigDecimal showCash;


    /** 实付赏金 */
    @NotNull
    private BigDecimal realCash;


    /** 已托管金额 */
    @NotNull
    private BigDecimal depositCash;


    /** 省 */
    @NotNull
    private Integer province;


    /** 城市 */
    @NotNull
    private Integer city;


    /** 地区 */
    @NotNull
    private Integer area;


    /** 浏览次数 */
    @NotNull
    private Integer viewCount;


    /** 投稿数量 */
    @NotNull
    private Integer deliveryCount;


    /** 用户编号 */
    private Integer uid;


    /** 用户名 */
    private String username;


    /** 服务商数量 */
    private Integer workerNum;


    /** 是否置顶 */
    @NotNull
    private Integer topStatus;


    /** 搜索引擎屏蔽 */
    @NotNull
    private Integer engineStatus;


    /** 稿件是否隐藏 */
    @NotNull
    private Integer workStatus;


    /** 增值服务编号 */
    private String service;


    /** 成功抽成比率 */
    @NotNull
    private Integer taskSuccessDrawRatio;


    /** 失败抽成比率 */
    @NotNull
    private Integer taskFailDrawRatio;


    /** 交付状态 */
    @NotNull
    private Integer keeStatus;


    /** 来源链接 */
    @NotBlank
    private String url;


    /** 来源网站 */
    @NotBlank
    private String siteName;


    /** 项目类型 */
    @NotNull
    private Integer type;


    /** 二级标签 */
    @NotBlank
    private String tagName;


    /** 一级标签 */
    @NotBlank
    private String tagPname;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    /** 用户职位*/
    @TableField(exist = false)
    private ZbUsers zbUsers;

    /** 用户职位*/
    @TableField(exist = false)
    private ZbTaskType zbTaskType;

    /** 用户职位*/
    @TableField(exist = false)
    private ZbTaskExtra zbTaskExtra;

    /** 用户职位*/
    @TableField(exist = false)
    private LocalStorage localStorage;

    /** 稿件信息*/
    @TableField(exist = false)
    private List<ZbWork> zbWork;

    public void copy(ZbTask source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
