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
* @author Guoxm
* @date 2020-07-17
*/
@Data
@TableName("zb_config")
public class ZbConfig implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 配置别名 */
    private String alias;


    /** 配置规则 */
    private String rule;


    /** 配置类型 */
    @NotBlank
    private String type;


    /** 配置名称 */
    private String title;


    /** 配置描述 */
    private String des;


    public void copy(ZbConfig source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
