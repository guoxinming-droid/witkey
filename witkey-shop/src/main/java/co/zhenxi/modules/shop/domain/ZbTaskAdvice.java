package co.zhenxi.modules.shop.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-20 10:48
 * @Description: ZbTaskAdvice
 **/
@Data
@TableName("zb_task")
public class ZbTaskAdvice extends ZbTask {
    //数量
    private Long count;

    private String cateName;
}
