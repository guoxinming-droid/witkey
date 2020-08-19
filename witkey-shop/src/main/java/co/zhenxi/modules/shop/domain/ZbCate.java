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
* @date 2020-07-20
*/
@Data
@TableName("zb_cate")
public class ZbCate implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 分类名称 */
    @NotBlank
    private String name;


    /** 父级分类 */
    @NotNull
    private Integer pid;

    /** 所属分类名称 */
    @NotBlank
    private String pname;

    /** 分类路径 */
    private String path;


    /** 分类图标 */
    private String pic;


    /** 排序 */
    @NotNull
    private Integer sort;


    /** 点击量 */
    @NotNull
    private Integer chooseNum;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    public void copy(ZbCate source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
