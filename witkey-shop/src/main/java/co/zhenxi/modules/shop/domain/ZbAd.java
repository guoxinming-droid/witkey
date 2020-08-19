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
@TableName("zb_ad")
public class ZbAd implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 广告位 */
    private Integer targetId;


    /** 广告类型 */
    private String adType;


    /** 位置 */
    private String adPosition;


    /** 广告名称 */
    private String adName;


    /** 广告文件 */
    private String adFile;


    /** 广告内容 */
    private String adContent;


    /** 广告url */
    private String adUrl;


    /** 开始时间 */
    private Timestamp startTime;


    /** 结束时间 */
    private Timestamp endTime;


    /** 用户编号 */
    private Integer uid;


    /** 用户名 */
    private String username;


    /** 排序 */
    private String listorder;


    /** 广告状态 */
    @NotNull
    private Integer isOpen;


    /** 创建时间 */
    private Timestamp createdAt;


    public void copy(ZbAd source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
