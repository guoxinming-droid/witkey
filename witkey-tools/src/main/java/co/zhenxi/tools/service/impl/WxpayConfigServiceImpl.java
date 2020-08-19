/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.tools.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.enums.UserStatusEnum;
import co.zhenxi.tools.domain.Ordertest;
import co.zhenxi.tools.domain.Unifiedorder;
import co.zhenxi.tools.domain.WxpayConfig;
import co.zhenxi.tools.service.OrdertestService;
import co.zhenxi.tools.service.WxpayConfigService;
import co.zhenxi.tools.service.dto.WxpayConfigDto;
import co.zhenxi.tools.service.dto.WxpayConfigQueryCriteria;
import co.zhenxi.tools.service.mapper.OrdertestMapper;
import co.zhenxi.tools.service.mapper.WxpayConfigMapper;
import co.zhenxi.tools.utils.*;
import co.zhenxi.utils.DateUtils;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.utils.RedisUtil;
import co.zhenxi.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.*;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author Guo
* @date 2020-07-20
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "wxpayConfig")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WxpayConfigServiceImpl extends BaseServiceImpl<WxpayConfigMapper, WxpayConfig> implements WxpayConfigService {

    private final IGenerator generator;
    private final OrdertestMapper ordertestMapper;
    private final WxpayConfigMapper wxpayConfigMapper;
    private final OrdertestService ordertestService;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(WxpayConfigQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<WxpayConfig> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), WxpayConfigDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<WxpayConfig> queryAll(WxpayConfigQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(WxpayConfig.class, criteria));
    }


    @Override
    public void download(List<WxpayConfigDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (WxpayConfigDto wxpayConfig : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("公众账号ID", wxpayConfig.getAppid());
            map.put("商户号", wxpayConfig.getMchId());
            map.put("设备号", wxpayConfig.getDeviceInfo());
            map.put("商户的key", wxpayConfig.getKey());
            map.put("api请求地址", wxpayConfig.getUrl());
            map.put("服务器异步通知页面路径", wxpayConfig.getNotifyUrl());
            map.put("服务器同步通知页面路径", wxpayConfig.getReturnUrl());
            map.put(" wxPackage",  wxpayConfig.getWxPackage());
            map.put("用户名", wxpayConfig.getUid());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> goWeChatPay(String orderId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isEmpty(orderId)) {
            map.put("code", UserStatusEnum.ERROR.intKey(0));
            map.put("msg", UserStatusEnum.ERROR.value(0));
            return map;
        }
        //获取订单信息
        Ordertest payParameter = ordertestMapper.selectByOrderId(orderId);
        double price = payParameter.getCash();
        System.out.println("price:" + price);
        // 微信开放平台审核通过的应用APPID

        //获取支付账号信息
        WxpayConfig wxpayconfig = wxpayConfigMapper.selectByUserId(payParameter.getUid());

        System.out.println("appid是：" + wxpayconfig.getAppid());
        System.out.println("mch_id是：" + wxpayconfig.getMchId());
        String nonce_str = StringUtils.getRandomString(30);
        System.out.println("随机字符串是：" + nonce_str);
        int total_fee = (int) (price * 100);

        String total_price = null;// 订单总金额，单位为分，详见支付金额
        String spbill_create_ip = WXSignUtils.getRemortIP(request);// "127.0.0.1";
        System.out.println("spbill_create_ip===="+spbill_create_ip);
        String notify_url = wxpayconfig.getNotifyUrl();
        System.out.println("notify_url是：" + notify_url);
        String trade_type = "APP";

        // 参数：开始生成签名
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", wxpayconfig.getAppid());
        parameters.put("body", payParameter.getTitle());
        parameters.put("mch_id", wxpayconfig.getMchId());
        parameters.put("nonce_str", nonce_str);
        parameters.put("notify_url", notify_url);
        parameters.put("out_trade_no", String.valueOf(payParameter.hashCode()));
/*
        parameters.put("total_fee", total_fee);
*/
        parameters.put("spbill_create_ip",spbill_create_ip);
        parameters.put("total_fee", 1);
        parameters.put("trade_type", trade_type);
        String sign = WXSignUtils.createSign("UTF-8", parameters);
        System.out.println("签名是：" + sign);
        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(wxpayconfig.getAppid());
        unifiedorder.setBody(payParameter.getTitle());
        unifiedorder.setMchId(wxpayconfig.getMchId());
        unifiedorder.setNonceStr(nonce_str);
        unifiedorder.setNotifyUrl(notify_url);
        unifiedorder.setOutTradeNo(String.valueOf(payParameter.getCode()));
        unifiedorder.setSpbillCreateIp(spbill_create_ip);
        unifiedorder.setTotalFee(1);
        unifiedorder.setTradeType(trade_type);
        unifiedorder.setSign(sign);

        // 构造xml参数
        String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
        System.out.println("xmlInfo:" + xmlInfo);

        String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String method = "POST";
        String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();// 请求微信
        System.out.println("weixinPost:" + weixinPost);
        UnifiedorderResult unifiedorderResult = ParseXMLUtils.jdomParseXml(weixinPost);// 解析微信的反馈
        if (unifiedorderResult != null) {
            if ("SUCCESS".equals(unifiedorderResult.getReturn_code())) {
                if("INVALID_REQUEST".equals(unifiedorderResult.getErr_code())){
                    map.put("code", UserStatusEnum.ERROR.intKey(0));
                    map.put("msg", "参数错误");
                    return map;
                }
                // 开始拼接App调起微信的参数
                SortedMap<Object, Object> wxAppparameters = new TreeMap<Object, Object>();
                wxAppparameters.put("appid", unifiedorderResult.getAppid());
                wxAppparameters.put("partnerid", unifiedorderResult.getMch_id());
                wxAppparameters.put("prepayid", unifiedorderResult.getPrepay_id());
                wxAppparameters.put("package", wxpayconfig.getWxPackage());
                wxAppparameters.put("noncestr", nonce_str);
                wxAppparameters.put("timestamp", String.valueOf(new Date().getTime()).substring(0, 10));
                wxAppparameters.put("sign", WXSignUtils.createSign("UTF-8", wxAppparameters));
                map.put("code", UserStatusEnum.SUCCESS.intKey(0));
                map.put("msg", UserStatusEnum.SUCCESS.value(0));
                map.put("data", wxAppparameters);
                return map;
            } else {
                System.out.println("错误原因为：" + unifiedorderResult.getReturn_msg());
                map.put("code", UserStatusEnum.ERROR.intKey(0));
                map.put("msg", unifiedorderResult.getReturn_msg());
                return map;
            }
        } else {
            System.out.println("服务端请求微信的返回值异常。");
            map.put("code", UserStatusEnum.ERROR.intKey(0));
            map.put("msg", "服务端请求微信的返回值异常。");
            return map;
        }
    }

    @SneakyThrows
    @Override
    public String weChatNotify(String orderId,HttpServletRequest request) {
        Ordertest order = ordertestMapper.selectByOrderId(orderId);
        Map<String, String> map = new HashMap<>();
        System.out.println("----------------微信回调开始啦----------------------");
        // 读取参数
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        // 解析xml成map
        Map<String, String> m = new HashMap<String, String>();
        m = WXSignUtils.doXMLParse(sb.toString());
        // 过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator<String> it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            System.out.println("p:" + parameter + ",v:" + v);
            packageParams.put(parameter, v);
        }

        //获取支付账号信息
        WxpayConfig wxpayconfig = wxpayConfigMapper.selectByUserId(order.getUid());
        // 微信支付的API密钥
        String key = wxpayconfig.getKey();
        if(!isTenpaySign("UTF-8", packageParams, key)){
            map.put("return_code", "FAIL");
            map.put("return_msg", "return_code不正确");
            return StringUtil.GetMapToXML(map);
        }
        //返回状态存入redis中
        if(m.get("return_code").equals("SUCCESS")){
            RedisUtil.set("wx"+m.get("out_trade_no"),m.get("result_code"),300);
        }
        if (isTenpaySign("UTF-8", packageParams, key)) {
            // 验证通过
            if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
                String out_trade_no = (String) packageParams.get("out_trade_no");
                /* 订单不为空 */
                if (!StringUtils.isEmpty(out_trade_no)) {
                    //支付成功后的业务处理
                //    OrderEntity order = orderMapper.getOrderInfo(Long.valueOf(out_trade_no));
                    if(!StringUtils.isEmpty(order)){
                        order.setId(order.getId());
                        order.setStatus(2);
                        order.setCreatedAt(Timestamp.valueOf(DateUtils.dateTime()));
                        ordertestService.saveOrUpdate(order);
                        System.out.println("----------------修改订单状态----------------------");
                    }
                    /* 添加支付信息 */
//                    Ordertest orderPay = new Ordertest();
//                    orderPay.setId(Long.valueOf(UUID.fromString(UUID.randomUUID())));
//                    orderPay.setId(order.getId());
//                    orderPay.setUid(order.getUserId());
//                    orderPay.setCash(order.getActualPrice());
//                    orderPay.setStatus(CalculatStaticConstant.CHECK_ONE);
//                    orderPay.setCreatedAt(Timestamp.valueOf(DateUtils.dateTime()));
//                    orderMapper.saveOrderPay(orderPay);
//                    System.out.println("----------------添加支付信息----------------------");
//                    map.put("return_code", "SUCCESS");
//                    map.put("return_msg", "OK");
//                    return StringUtil.GetMapToXML(map);
                }
            }
        } else {
            System.out.println("支付失败");
            map.put("return_code", "error");
            map.put("return_msg", "支付失败");
            return StringUtil.GetMapToXML(map);
        }
        System.out.println("支付失败");
        System.out.println("支付失败");
        map.put("return_code", "error");
        map.put("return_msg", "支付失败");
        return StringUtil.GetMapToXML(map);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams,
                                       String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + API_KEY);
        // 算出摘要
        String mysign = MD5Util.md5Encrypt32Upper(sb.toString()).toLowerCase();
        String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();
        return tenpaySign.equals(mysign);
    }

}
