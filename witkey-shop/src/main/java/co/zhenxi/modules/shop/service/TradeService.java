package co.zhenxi.modules.shop.service;

import co.zhenxi.common.service.BaseService;
import co.zhenxi.modules.shop.domain.Trade;

public interface TradeService extends BaseService<Trade> {

    /**
     * 新增
     */
    void add(Trade trade);



    /**
     * 修改订单状态
     * 此方法会在支付宝的异步调用的使用
     * 根据传入的商户订单号直接修改 无需判断
     */
    void updateOutTradeNo(String outTradeNo);

    void updateTradeNoReturn(String tradeNo, String outTradeNo);

    /**
     * 根据我方的订单号查询数据
     * @param tradeNo
     * @return
     */
    Trade getByTradeNo(String tradeNo);
}
