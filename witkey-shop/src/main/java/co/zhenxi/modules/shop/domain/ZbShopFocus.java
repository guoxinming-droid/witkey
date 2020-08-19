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
* @date 2020-08-19
*/
@Data
@TableName("zb_shop_focus")
public class ZbShopFocus implements Serializable {

    /** 店铺关注关联序号 */
    @TableId
    private Integer id;


    /** 关注者id */
    @NotNull
    private Integer uid;


    /** 店铺id */
    @NotNull
    private Integer shopId;


    @NotNull
    private Timestamp createdAt;


    public void copy(ZbShopFocus source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
