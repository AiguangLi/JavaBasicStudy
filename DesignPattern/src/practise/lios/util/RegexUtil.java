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
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobile);

        return matcher.matches();
    }

    public static boolean isEmail(String email) {
        String regex = "^\\w+([._]*\\w)*@\\w+([.\\-_]*\\w)*[.]\\w+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    };
}