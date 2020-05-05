package com.lios;

/**
 * 懒加载方式的单例模式：可能存在线程安全问题，可以在getInstance()方法前加synchronized
 * 通过static int 验证number在多线程中被多次增加
 *
 * @author liaiguang
 */
public class LazySingletonDemo {
    private static LazySingletonDemo instance = null;
    private static int number;

    private LazySingletonDemo() {
        LazySingletonDemo.number ++;
        System.out.println("懒加载模式的单例!");
    }

    /**
     * 懒加载的获取实例方法，若不在方法前加synchronized会有线程安全问题
     *
     * @return LazySingletonDemo
     */
    public static LazySingletonDemo getInstance() {
        if (instance == null) {
            instance = new LazySingletonDemo();
        }

        return instance;
    }

    public void showNumber() {
        System.out.println("Lazy number: " + LazySingletonDemo.number);
    }

    public void say(String name) {
        System.out.println("Hello " + name);
    }

}
