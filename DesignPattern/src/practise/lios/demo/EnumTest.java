package practise.lios.demo;

import java.util.Scanner;

/**
 * @author liaiguang
 * @created 2020/5/31
 */
public class EnumTest {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        System.out.println("Input a size (SMALL, MEDIUM, LARGE, EXTRA_LARGE): ");
        String input = in.next().toUpperCase();

        try {
            Size size = Enum.valueOf(Size.class, input);

            System.out.println("abbreviation = " + size);
            if (size == Size.EXTRA_LARGE) {
                System.out.println("XL Selected");
            }

            size.handle();
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
    }
}

/**
 * 枚举本质是是一个特殊的类
 * 构造函数是私有的
 * 枚举值是有限的固定值的实例
 */
enum Size {
    /**
     * SMALL: S
     * Medium: M
     * LARGE: L
     * EXTRA_LARGE: XL
     */
    SMALL("S") {
        @Override
        void handle() {
            System.out.println("Small Handler");
        }
    },
    MEDIUM("M") {
        @Override
        void handle() {
            System.out.println("Medium Handler");
        }
    },
    LARGE("L") {
        @Override
        void handle() {
            System.out.println("Large Handler");
        }
    },
    EXTRA_LARGE("XL") {
        @Override
        void handle() {
            System.out.println("ExtraLarge Handler");
        }
    };

    void handle() {
        System.out.println("Basic Handler");
    }

    private final String abbreviation;
    private Size(String abbreviation) {this.abbreviation = abbreviation;}
    public String getAbbreviation() {
        return abbreviation;
    }
}