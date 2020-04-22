package com.lios.models;

/**
 * @author liaiguang
 */
public class ApplePhone extends Phone {
    public ApplePhone(String name, float memory, float diskSize) {
        super(name, memory, diskSize);
    }

    @Override
    public void call() {
        System.out.println("iPhone calls");
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
