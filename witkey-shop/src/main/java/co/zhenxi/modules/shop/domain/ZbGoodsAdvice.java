package co.zhenxi.modules.shop.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-11 17:44
 * @Description: ZbGoodsAdvice
 **/
@Data
@ToString
public class ZbGoodsAdvice extends ZbGoods {

    private String cityName;

    private String types;

    private String userName;

    private String tagName;

    private String tagPname;
}
