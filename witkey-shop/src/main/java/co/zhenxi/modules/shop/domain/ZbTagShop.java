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
import java.io.Serializable;

/**
* @author guoke
* @date 2020-08-15
*/
@Data
@TableName("zb_tag_shop")
public class ZbTagShop implements Serializable {

    @TableId
    private Integer id;


    /** kppw_skill_tags表的主键id */
    @NotNull
    private Integer tagId;


    /** kppw_shop表的主键id即店铺id */
    @NotNull
    private Integer shopId;

    private  String tagName;

    public void copy(ZbTagShop source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
