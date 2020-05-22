package com.lios.factory;

/**
 * @author liaiguang
 */
public class YellowHuman extends AbstractHuman {
    public void getSkinColor() {
        System.out.println("黄皮肤");
    }

    public void speak() {
        System.out.println("黄种人一般使用双字节说话");
    }
}
