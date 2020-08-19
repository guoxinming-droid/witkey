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
* @date 2020-07-22
*/
@Data
@TableName("zb_task_extra")
public class ZbTaskExtra implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 任务编号 */
    @NotNull
    private Integer taskId;


    /** seo标题 */
    private String seoTitle;


    /** seo关键词 */
    private String seoKeyword;


    /** seo描述 */
    private String seoContent;


    /** 创建时间 */
    private Timestamp createdAt;


    /** 修改时间 */
    private Timestamp updatedAt;


    public void copy(ZbTaskExtra source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
