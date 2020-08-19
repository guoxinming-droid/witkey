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
* @date 2020-08-15
*/
@Data
@TableName("zb_skill_tags")
public class ZbSkillTags implements Serializable {

    @TableId
    private Integer id;


    /** 技能标签 */
    @NotBlank
    private String tagName;


    /** 关联的任务类型 */
    @NotNull
    private Integer cateId;


    private Timestamp createdAt;


    private Timestamp updatedAt;


    public void copy(ZbSkillTags source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
