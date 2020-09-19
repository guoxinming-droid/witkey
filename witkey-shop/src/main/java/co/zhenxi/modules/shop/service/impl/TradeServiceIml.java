package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.modules.shop.domain.Trade;
import co.zhenxi.modules.shop.service.TradeService;
import co.zhenxi.modules.shop.service.mapper.TradeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-19 11:10
 * @Description: TradeServiceIml
 **/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbActivity")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TradeServiceIml extends BaseServiceImpl<TradeMapper, Trade> implements TradeService {

    private TradeMapper tradeMapper;




    /**
     * 新增
     *
     * @param trade
     */
    @Override
    public void add(Trade trade) {
        tradeMapper.insert(trade);
    }

    /**
     * 修改订单状态
     * 此方法会在支付宝的异步调用的使用
     * 根据传入的商户订单号直接修改 无需判断
     *
     * @param outTradeNo
     */
    @Override
    public void updateOutTradeNo(String outTradeNo) {
        tradeMapper.updateOutTradeNo(outTradeNo);
    }

    @Override
    public void updateTradeNoReturn(String tradeNo, String outTradeNo) {
        tradeMapper.updateTradeNoReturn(tradeNo,outTradeNo);
    }

    /**
     * 根据我方的订单号查询数据
     *
     * @param tradeNo
     * @return
     */
    @Override
    public Trade getByTradeNo(String tradeNo) {

        return tradeMapper.selectByTradeNo(tradeNo);
    }
}
