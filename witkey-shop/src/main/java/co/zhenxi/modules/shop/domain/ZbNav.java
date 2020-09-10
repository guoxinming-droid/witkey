package co.zhenxi.modules.shop.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-31 14:24
 * @Description: ZbNav
 **/
@Data
@TableName("zb_nav")
public class ZbNav {

    @TableId
    private Integer id;
    /**
     * 标题
     */
    @NotBlank
    private String title;
    /**
     * 跳转链接
     */
    @NotBlank
    private String linkUrl;
    /**
     * 样式
     */
    private String style;
    /**
     * 排序字段
     */
    private byte sort;
    /**
     * 是否新窗口打开 1 打开 2否
     */
    private byte isNewWindow;
    /**
     * 显示模式 1 是 2 否
     */
    private byte isShow;

    private String codeName;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
