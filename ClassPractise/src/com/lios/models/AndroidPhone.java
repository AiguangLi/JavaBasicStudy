package com.lios.models;

/**
 * @author liaiguang
 */
public class AndroidPhone extends Phone {
    public AndroidPhone(String name, float memory, float diskSize) {
        super(name, memory, diskSize);
    }

    @Override
    public void call() {
        System.out.println("Android Phone calls");
    }
}
