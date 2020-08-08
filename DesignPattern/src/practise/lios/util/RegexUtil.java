package practise.lios.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liaiguang
 * @date 2020/8/8
 */
public class RegexUtil {
    public static boolean isChinaMobile(String mobile) {
        String regex = "^1[3-9]\\d{9}$";

        return patterMatch(regex, mobile);
    }

    public static boolean isEmail(String email) {
        String regex = "^\\w+([._]*\\w)*@\\w+([.\\-_]*\\w)*[.]\\w+$";

        return patterMatch(regex, email);
    };

    public static boolean isUrl(String url) {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]$";

        return patterMatch(regex, url.toLowerCase());
    }

    public static boolean isAscString(String str) {
        String regex = "^[\\x00-\\x7F]+$";

        return patterMatch(regex, str);
    }

    public static boolean isInteger(String str) {
        String regex = "^[-|+]?[0-9]+$";

        return patterMatch(regex, str);
    }

    /**
     * 浮点数校验，支持+/-符号，最多6位小数
     * @param str=要检验的字符
     * @param pointNum=小数位数
     * @return boolean
     */
    public static boolean isFloat(String str, int pointNum) {
        final int minPointNum = 1;
        final int maxPointNum = 6;
        if (pointNum < minPointNum) {
            pointNum = minPointNum;
        }
        if (pointNum > maxPointNum) {
            pointNum = maxPointNum;
        }
        String regex = String.format("^[-|+]?[0-9]+(.[0-9]{1,%d}){0,1}$", pointNum);
       // String regex = "^[-|+]?[0-9]+(.[0-9]{1,6}){0,1}$";

        return patterMatch(regex, str);
    }

    public static boolean patterMatch(String regex, String target) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);

        return matcher.matches();
    }
}