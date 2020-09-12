package co.zhenxi.modules.shop.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-22 14:46
 * @Description: ZbShopAdvice
 **/
@Data
public class ZbShopAdvice extends ZbShop {

    private String num;

    private List list;

    private String pName;

    private String sName;

    private String cityName;

    private Integer provinceName;

    private List<ZbTagShop> tagShop;

    private List<ZbGoods> goods;

    private String userName;


    /**
     * 好评率
     */
    private Integer goodRate;


}
