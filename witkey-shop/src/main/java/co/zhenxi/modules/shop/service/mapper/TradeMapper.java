package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.Trade;
import co.zhenxi.modules.shop.domain.ZbActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TradeMapper extends CoreMapper<Trade> {
    @Update("update trade set status = 1 where out_trade_no = #{outTradeNo}")
    void updateOutTradeNo(String outTradeNo);

    @Update("update trade set trade_no_return = #{tradeNo}  where out_trade_no = #{outTradeNo}")
    void updateTradeNoReturn(String tradeNo, String outTradeNo);

    @Select("select * from trade where trade_no = #{tradeNo}")
    Trade selectByTradeNo(String tradeNo);
}
