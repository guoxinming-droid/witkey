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
import java.io.Serializable;

/**
* @author guoke
* @date 2020-07-23
*/
@Data
@TableName("zb_goods_comment")
public class ZbGoodsComment implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 商品编号 */
    @NotNull
    private Integer goodsId;


    /** 用户 */
    @NotNull
    private Integer uid;


    /** 商品评价对象 */
    @NotNull
    private Integer commentBy;


    /** 速度得分 */
    @NotNull
    private Double speedScore;


    /** 质量得分 */
    @NotNull
    private Double qualityScore;


    /** 态度得分 */
    @NotNull
    private Double attitudeScore;


    /** 评价内容 */
    @NotBlank
    private String commentDesc;


    /** 评价类型 */
    @NotNull
    private Integer type;


    /** 评价时间 */
    @NotNull
    private Timestamp createdAt;


    public void copy(ZbGoodsComment source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
