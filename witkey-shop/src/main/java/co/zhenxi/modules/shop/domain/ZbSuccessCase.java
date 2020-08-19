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
* @date 2020-07-30
*/
@Data
@TableName("zb_success_case")
public class ZbSuccessCase implements Serializable {

    @TableId
    private Integer id;


    /** 创建用户ID */
    @NotNull
    private Integer uid;


    /** 发布人name */
    @NotBlank
    private String username;


    /** 成功案例标题 */
    @NotBlank
    private String title;


    /** 成功案例描述 */
    private String des;


    /** 成功案例跳转链接 */
    private String url;


    /** 成功案例封面 */
    private String pic;


    /** 成功案例分类 */
    @NotNull
    private Integer cateId;


    /** 成功案例发布方式: 0->平台 1->用户 */
    @NotNull
    private Integer type;


    /** 发布者id */
    @NotNull
    private Integer pubUid;


    /** 访问次数 */
    @NotNull
    private Integer viewCount;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 金额 */
    @NotNull
    private BigDecimal cash;


    public void copy(ZbSuccessCase source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
