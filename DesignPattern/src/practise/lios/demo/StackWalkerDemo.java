package practise.lios.demo;

/**
 * @author liaiguang
 * @created 2020/6/7
 */
public class StackWalkerDemo {
    public static int factional(int n) {
        int r;
        System.out.println("factional(" + n + ")");
        //StackWalker (Java 9以后)可以使用流的方式打印调用堆栈信息
        StackWalker walker = StackWalker.getInstance();
        walker.forEach(System.out::println);
        if (n <= 1) {
            r = 1;
        } else {
            r = n * factional(n-1);
        }
        System.out.println("return: " + r);

        return r;
    }

    public static void main(String[] args) {
        StackWalkerDemo.factional(4);
    }
}