package co.zhenxi.modules.shop.domain;

import lombok.Data;

import java.util.Map;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-21 15:07
 * @Description: ZbUserDetailAdvice
 **/
@Data
public class ZbUserDetailAdvice extends ZbUserDetail{
    private Map authenticationList;

    private String cateName;

    private String userName;

    private String shopId;
}
