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
    }
}