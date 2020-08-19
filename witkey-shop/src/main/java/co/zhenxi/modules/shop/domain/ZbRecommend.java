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
* @author Guoxm
* @date 2020-07-16
*/
@Data
@TableName("zb_recommend")
public class ZbRecommend implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 推荐位 */
    @NotNull
    private Integer positionId;


    /** 类型 */
    @NotBlank
    private String type;


    /** 推荐编号 */
    @NotNull
    private Integer recommendId;


    /** 推荐类型 */
    @NotBlank
    private String recommendType;


    /** 推荐名称 */
    @NotBlank
    private String recommendName;


    /** 推荐图片 */
    @NotBlank
    private String recommendPic;


    /** 跳转链接 */
    @NotBlank
    private String url;


    /** 开始时间 */
    private Timestamp startTime;


    /** 结束时间 */
    private Timestamp endTime;


    /** 排序 */
    @NotNull
    private Integer sort;


    /** 是否开启 1-开启 2-关闭 3-删除 */
    @NotNull
    private Integer isOpen;


    /** 创建时间 */
    private Timestamp createdAt;


    public void copy(ZbRecommend source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
