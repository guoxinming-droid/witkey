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
@TableName("zb_link")
public class ZbLink implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 标题 */
    @NotBlank
    private String title;


    /** 链接名称 */
    private String content;


    /** 添加时间 */
    private Timestamp addtime;


    /** 状态 */
    @NotNull
    private Integer status;


    /** 排序 */
    private Integer sort;


    private String pic;


    public void copy(ZbLink source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
