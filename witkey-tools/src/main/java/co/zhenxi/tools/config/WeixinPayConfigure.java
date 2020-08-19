package co.zhenxi.tools.config;

public class WeixinPayConfigure {

    /**
     * 域名 项目域名
     */
    public static final String ROOTURL = "http://www.uxuexi.com";
    /**
     * 域名 请求微信时的路径
     */
    public static final String ORDER_ROOTURL = "http://order.uxuexi.com";
    /**
     * 商户id （商户号）
     */
    public static final String MCH_ID = "1111111111";
    /**
     * 公共账号id                       （18位数↓）
     */
    public static final String APPID = "wx111111111111111c";
    /**
     * 应用秘钥（AppSecret）  在公众号平台上找↓
     */
    public static final String APP_SECRET = "f4e04138de2b34b75a645c13a654f815";
    /**API秘钥*/
    public static final String API_KEY = "shemadongxi1243432sdf3213";
    /**
     * 统一下单URL
     */
    public static final String PAY_UNIFIED_ORDER_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信公众号交易类型 （扫码支付类型：NATIVE，公众号支付类型：JSAPI）
     */
    public static final String TRADE_TYPE = "NATIVE";
    /**
     * 微信支付成功之后的回调 （微信支付成功后调用）
     */
    public static final String NOTIFY_ACTIVITY_URL = ORDER_ROOTURL + "/pay/wx/wxnotify.json";

}
