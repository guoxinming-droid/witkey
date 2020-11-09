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
* @date 2020-08-21
*/
@Data
@TableName("zb_employ_work")
public class ZbEmployWork implements Serializable {

    @TableId
    private Integer id;


    /** 稿件描述 */
    @NotBlank
    private String desc;


    /** 稿件id */
    @NotNull
    private Integer employId;


    /** 状态 0表示没有验收 1表示验收 */
    @NotNull
    private Integer status;


    /** 交稿威客id */
    @NotNull
    private Integer uid;


    /** 文件后缀 */
    @NotBlank
    private String type;


    /** 创建时间 */
    @NotNull
    private Timestamp createdAt;


    public void copy(ZbEmployWork source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
