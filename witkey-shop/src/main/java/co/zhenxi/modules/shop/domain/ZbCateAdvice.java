package co.zhenxi.modules.shop.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-24 11:03
 * @Description: ZbCateAdvice
 **/
@Data
public class ZbCateAdvice extends ZbCate {

    private ZbCate PzbCate;

    private List<ZbCate> list;
}
