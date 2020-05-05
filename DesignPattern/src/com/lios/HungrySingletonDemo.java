package com.lios;

/**
 * 饥饿方式单例模式：在获取前就已经初始化好了实例对象
 * 优点：线程安全
 * 缺点：对象没用到的时候也会创建
 *
 * @author liaiguang
 */
public class HungrySingletonDemo {
    private static final HungrySingletonDemo INSTANCE = new HungrySingletonDemo();
    private static int number;


    private HungrySingletonDemo() {
        HungrySingletonDemo.number++;
        System.out.println("饥饿模式的单例!");
    }

    public static HungrySingletonDemo getInstance() {
        return INSTANCE;
    }

    public void showNumber() {
        System.out.println("Hungry number: " + HungrySingletonDemo.number);
    }

    public void say(String name) {
        System.out.println("Hello " + name);
    }
}
