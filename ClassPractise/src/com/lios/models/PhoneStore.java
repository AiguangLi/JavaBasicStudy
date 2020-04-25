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

    public void show() {
        System.out.println(phoneToSell.toString());
    }
}
