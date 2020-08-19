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
@TableName("zb_recommend_position")
public class ZbRecommendPosition implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 推荐位名称 */
    @NotBlank
    private String name;


    /** 推荐位别名 */
    @NotBlank
    private String code;


    /** 推荐位位置描述 */
    @NotBlank
    private String position;


    /** 推荐位图片 */
    private String pic;


    /** 推荐数量 */
    @NotNull
    private Integer num;


    /** 状态 */
    @NotNull
    private Integer isOpen;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    public void copy(ZbRecommendPosition source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
