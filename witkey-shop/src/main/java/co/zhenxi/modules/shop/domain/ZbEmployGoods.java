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
* @date 2020-07-28
*/
@Data
@TableName("zb_employ_goods")
public class ZbEmployGoods implements Serializable {

    @TableId
    private Integer id;


    /** 关联雇佣id */
    @NotNull
    private Integer employId;


    /** 关联服务id */
    @NotNull
    private Integer serviceId;


    /** 关联创建时间 */
    @NotNull
    private Timestamp createdAt;


    public void copy(ZbEmployGoods source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
