package co.zhenxi.tools.utils;

import java.util.Map;

public class StringUtil {

    /**
     * @Function: 将map转换为xml
     * @author:   YangXueFeng
     * @Date:     2019/6/19 11:32
     */
    public static String GetMapToXML(Map<String,String> param){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (Map.Entry<String,String> entry : param.entrySet()) {
            sb.append("<"+ entry.getKey() +">");
            sb.append(entry.getValue());
            sb.append("</"+ entry.getKey() +">");
        }
        sb.append("</xml>");
        return sb.toString();
    }
}
