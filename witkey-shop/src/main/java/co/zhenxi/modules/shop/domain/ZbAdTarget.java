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
* @date 2020-07-16
*/
@Data
@TableName("zb_ad_target")
public class ZbAdTarget implements Serializable {

    /** 广告位编号 */
    @TableId
    private Integer targetId;


    /** 广告位名称 */
    private String name;


    /** 广告位标签 */
    private String code;


    /** 描述 */
    private String description;


    /** 广告标签 */
    private String targets;


    /** 广告位置 */
    private String position;


    /** 广告位大小 */
    private String adSize;


    /** 广告位个数 */
    private Integer adNum;


    /** 广告位图片 */
    private String pic;


    /** 是否开启 */
    private Integer isOpen;


    /** 内容 */
    private String content;


    public void copy(ZbAdTarget source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
