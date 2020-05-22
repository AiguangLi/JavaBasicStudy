package com.lios.singleton;

import java.util.ArrayList;
import java.util.Random;

/**
 * 有限数量的实例化
 * 提前生成一定数量的对象，供程序调用
 * 例如数据库连接池，I/O连接池，以便减少耗时长的对象创建开销
 * 即拿即用
 *
 * @author liaiguang
 */
public class LimitInstanceDemo {
    private static final int MAXIMUM_INSTANCE_NUMBER = 5;
    private static ArrayList<LimitInstanceDemo> instancePool = new ArrayList<LimitInstanceDemo>(MAXIMUM_INSTANCE_NUMBER);
    private int index = 0;

    static {
        for (int i = 0; i < MAXIMUM_INSTANCE_NUMBER; ++i) {
            instancePool.add(new LimitInstanceDemo(i));
        }
    }
    private LimitInstanceDemo(int index) {
        this.index = index;
        System.out.println("index = " + index);
    }

    public static LimitInstanceDemo getInstance() {
        int index = (new Random()).nextInt(MAXIMUM_INSTANCE_NUMBER);
        return instancePool.get(index);
    }

    public void say() {
        System.out.println("index = " + index);

    }
}
