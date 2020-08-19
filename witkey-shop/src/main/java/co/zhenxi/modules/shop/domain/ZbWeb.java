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
* @author Guo xinming
* @date 2020-07-16
*/
@Data
@TableName("zb_web")
public class ZbWeb implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 名称 */
    private String name;


    /** 活动链接 */
    private String url;


    /** 排序 */
    @NotNull
    private Integer sort;


    /** 栏目分类 */
    private Integer webCateId;


    /** 状态 */
    @NotNull
    private Integer status;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    public void copy(ZbWeb source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
