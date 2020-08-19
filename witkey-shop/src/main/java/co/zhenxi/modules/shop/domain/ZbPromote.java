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
* @date 2020-08-06
*/
@Data
@TableName("zb_promote")
public class ZbPromote implements Serializable {

    /** 推广列表自增id */
    @TableId
    private Integer id;


    /** 推广人id */
    @NotNull
    private Integer fromUid;


    /** 被推广人id */
    @NotNull
    private Integer toUid;


    /** 推广成功获得推广金额 */
    @NotNull
    private BigDecimal price;


    /** 完成推广的条件  1=>实名认证  2=>邮箱认证 3=>支付认证 */
    @NotNull
    private Integer finishConditions;


    /** 推广类型  1=>注册推广 */
    @NotNull
    private Integer type;


    /** 推广状态  1=>推广中  2=>完成 */
    @NotNull
    private Integer status;


    /** 推广时间 */
    @NotNull
    private Timestamp createdAt;


    /** 完成推广时间 */
    @NotNull
    private Timestamp updatedAt;


    public void copy(ZbPromote source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
