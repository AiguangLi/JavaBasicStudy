package com.lios.singleton;

/**
 * @author liaiguang
 */
public class Example implements Runnable {
    static final int MAXIMUM_LOOP_NUMBER = 10;

    @Override
    public void run() {
        LazySingletonDemo lazyInstance = LazySingletonDemo.getInstance();
        lazyInstance.showNumber();

        HungrySingletonDemo hungryInstance = HungrySingletonDemo.getInstance();
        hungryInstance.showNumber();
    }

    public static void main(String[] args) {
        //HungrySingletonDemo instance = HungrySingletonDemo.getInstance();
        //instance.say("Hungry Singleton");

        //LazySingletonDemo lazyInstance = LazySingletonDemo.getInstance();
        //lazyInstance.say("Lazy Singleton");

        Example example = new Example();
        for (int i = 0; i < MAXIMUM_LOOP_NUMBER; ++i) {
            Thread thread = new Thread(example);
            thread.start();
        }

        for (int i=0; i < MAXIMUM_LOOP_NUMBER; ++i) {
            LimitInstanceDemo instance = LimitInstanceDemo.getInstance();
            instance.say();
        }
    }
}
