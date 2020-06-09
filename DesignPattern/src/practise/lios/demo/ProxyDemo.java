package practise.lios.demo;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @author liaiguang
 * @created 2020/6/7
 */
public class ProxyDemo {
    public static void main(String[] args) {
        var elements = new Object[100];
        for(int i = 0; i < elements.length; ++i) {
            Integer value = i;
            var handler = new ComparableProxy(value);
            Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;

        int result = Arrays.binarySearch(elements, key);

        if (result >= 0) {
            System.out.print(elements[result]);
        }
    }
}