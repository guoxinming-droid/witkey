/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.zhenxi.tools.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.exception.BadRequestException;
import co.zhenxi.tools.domain.AlipayConfig;
import co.zhenxi.tools.domain.vo.TradeVo;
import co.zhenxi.tools.service.AlipayConfigService;
import co.zhenxi.tools.service.mapper.AlipayConfigMapper;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;


/**
* @author hupeng
* @date 2020-05-13
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "alipayConfig")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AlipayConfigServiceImpl extends BaseServiceImpl<AlipayConfigMapper, AlipayConfig> implements AlipayConfigService {


    private AlipayConfigMapper alipayConfigMapper;

    @Override
    public String toPayAsPc(AlipayConfig alipay, TradeVo trade) throws Exception {
        if(alipay.getId() == null){
            throw new BadRequestException("请先添加相应配置，再操作");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppId(), alipay.getPrivateKey(), alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());

        // 创建API对应的request(电脑网页版)
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        // 订单完成后返回的页面和异步通知地址
        request.setReturnUrl(alipay.getReturnUrl());
        request.setNotifyUrl(alipay.getNotifyUrl());
        // 填充订单参数
        request.setBizContent("{" +
                "    \"out_trade_no\":\""+trade.getOutTradeNo()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+trade.getTotalAmount()+"," +
                "    \"subject\":\""+trade.getSubject()+"\"," +
                "    \"body\":\""+trade.getBody()+"\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+alipay.getSysServiceProviderId()+"\"" +
                "    }"+
                "  }");//填充业务参数
        // 调用SDK生成表单, 通过GET方式，口可以获取url
        return alipayClient.pageExecute(request, "GET").getBody();

    }

    @Override
    public String toPayAsWeb(AlipayConfig alipay, TradeVo trade) throws Exception {
        if(alipay.getId() == null){
            throw new BadRequestException("请先添加相应配置，再操作");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppId(), alipay.getPrivateKey(), alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());

        double money = Double.parseDouble(trade.getTotalAmount());
        double maxMoney = 5000;
        if(money <= 0 || money >= maxMoney){
            throw new BadRequestException("测试金额过大");
        }
        // 创建API对应的request(手机网页版)
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setReturnUrl(alipay.getReturnUrl());
        request.setNotifyUrl(alipay.getNotifyUrl());
        request.setBizContent("{" +
                "    \"out_trade_no\":\""+trade.getOutTradeNo()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+trade.getTotalAmount()+"," +
                "    \"subject\":\""+trade.getSubject()+"\"," +
                "    \"body\":\""+trade.getBody()+"\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+alipay.getSysServiceProviderId()+"\"" +
                "    }"+
                "  }");
        return alipayClient.pageExecute(request, "GET").getBody();
    }

    @Override
//    @Cacheable(key = "'1'")
    public AlipayConfig find() {
        AlipayConfig alipayConfig = this.list().get(0);
        return alipayConfig;
    }

    @Override
//    @CachePut(key = "'1'")
    @Transactional(rollbackFor = Exception.class)
    public void update(AlipayConfig alipayConfig) {
         this.save(alipayConfig);
    }

    /**
     * 向支付宝发起订单查询请求
     *
     * @param outTradeNo
     * @return String
     */
    @Override
    public String checkAlipay(String outTradeNo) {
        //ServiceImpl.log.info("==================向支付宝发起查询，查询商户订单号为：" + outTradeNo);


        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型）

            AlipayConfig alipayConfig =find();
            AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getAppId(),
                    alipayConfig.getPrivateKey(), alipayConfig.getFormat(), alipayConfig.getCharset(),
                    alipayConfig.getPublicKey(), alipayConfig.getSignType());
            AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
            alipayTradeQueryRequest.setBizContent("{" +
                    "\"out_trade_no\":\"" + outTradeNo + "\"" +
                    "}");
            AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.execute(alipayTradeQueryRequest);
            if (alipayTradeQueryResponse.isSuccess()) {
                String str = "";
                //修改数据库支付宝订单表

                // 判断交易结果
                switch (alipayTradeQueryResponse.getTradeStatus())
                {
                    // 交易结束并不可退款
                    case "TRADE_FINISHED":
                        str = "交易结束并不可退款:TRADE_FINISHED";
                        break;
                    // 交易支付成功
                    case "TRADE_SUCCESS":
                        str = "交易支付成功:TRADE_SUCCESS";
                        break;
                    // 未付款交易超时关闭或支付完成后全额退款
                    case "TRADE_CLOSED":
                        str = "未付款交易超时关闭或支付完成后全额退款:TRADE_CLOSED";
                        break;
                    // 交易创建并等待买家付款
                    case "WAIT_BUYER_PAY":
                        str = "交易创建并等待买家付款:WAIT_BUYER_PAY";
                        break;
                    default:
                        break;
                }
                //更新表记录
                return str;
            } else {
                //log.info("==================调用支付宝查询接口失败！");
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "fail";

    }

    /**
     * 修改订单状态
     *
     * @param outTradeNo@return
     *
     */
    @Override
    public void updateOutTradeNo(String outTradeNo) {
        alipayConfigMapper.updateOutTradeNo(outTradeNo);
    }
}
