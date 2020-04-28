package com.lios.models;

/**
 * @author liaiguang
 */
public class PhoneStore {
    Phone phoneToSell;

    public PhoneStore(Phone phone) {
        ///同一个包内的可以访问包构造方法
        phoneToSell = new Phone(phone);
    }

    /**
     * 基类对象可以协变转换为子类，从而以组合方式实现多态行为
     */
    public void changePhone() {
        phoneToSell = new ApplePhone();
    }

    public void call() {
        phoneToSell.call();
    }

    public void show() {
        System.out.println(phoneToSell.toString());
    }
}
