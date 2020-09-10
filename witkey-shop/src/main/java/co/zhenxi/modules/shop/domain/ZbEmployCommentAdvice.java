package co.zhenxi.modules.shop.domain;

import lombok.Data;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-10 16:38
 * @Description: ZbEmployCommentAdvice
 **/
@Data
public class ZbEmployCommentAdvice extends ZbEmployComment {
    private Integer serviceCount;

    private Integer workCount;
}
