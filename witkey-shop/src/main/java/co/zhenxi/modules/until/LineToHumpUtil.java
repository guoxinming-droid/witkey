package co.zhenxi.modules.until;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-18 10:16
 * @Description: LineToHumpUtil
 **/
public class LineToHumpUtil {
    private static final Pattern linePattern = Pattern.compile("_(\\w)");
    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
