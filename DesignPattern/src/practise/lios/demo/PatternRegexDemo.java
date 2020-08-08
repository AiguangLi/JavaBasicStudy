package practise.lios.demo;

import practise.lios.util.RegexUtil;

import java.util.Scanner;

/**
 * @author liaiguang
 * @date 2020/8/8
 */
public class PatternRegexDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String exit = "q";
        String mobile;
        System.out.println("Input mobile to test: ");
        while(! exit.equals((mobile = scanner.nextLine()).toLowerCase())) {
            System.out.println("Is China Mobile: " + RegexUtil.isChinaMobile(mobile));
            System.out.println("Input mobile to test: ");
        }

        String email;
        System.out.println("Input email to test: ");
        while(! exit.equals((email = scanner.nextLine()).toLowerCase())) {
            System.out.println("Is Email: " + RegexUtil.isEmail(email));
            System.out.println("Input Email to test: ");
        }

        String url;
        System.out.println("Input url to test: ");
        while(! exit.equals((url = scanner.nextLine()).toLowerCase())) {
            System.out.println("Is url: " + RegexUtil.isUrl(url));
            System.out.println("Input url to test: ");
        }

        String asc;
        System.out.println("Input asc to test: ");
        while(! exit.equals((asc = scanner.nextLine()).toLowerCase())) {
            System.out.println("Is asc: " + RegexUtil.isAscString(asc));
            System.out.println("Input asc to test: ");
        }

        String integer;
        System.out.println("Input integer to test: ");
        while(! exit.equals((integer = scanner.nextLine()).toLowerCase())) {
            boolean isInteger = RegexUtil.isInteger(integer);
            System.out.println("Is integer: " + isInteger);
            if (isInteger) {
                int value = Integer.parseInt(integer);
                System.out.println("Integer Value: " + value);
            }
            System.out.println("Input integer to test: ");
        }

        String floatStr;
        System.out.println("Input Float to test: ");
        while(! exit.equals((floatStr = scanner.nextLine()).toLowerCase())) {
            boolean isFloat = RegexUtil.isFloat(floatStr, 1);
            System.out.println("Is float: " + isFloat);
            if (isFloat) {
                float value = Float.parseFloat(floatStr);
                System.out.println("Float Value: " + value);
            }
            System.out.println("Input Float to test: ");
        }
    }
}