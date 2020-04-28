package com.lios.models;

/**
 * @author liaiguang
 */
public class ApplePhone extends Phone {
    public ApplePhone(String name, float memory, float diskSize) {
        super(name, memory, diskSize);
    }

    /**
     * 默认构造函数会自动调用父类的默认构造函数，无法重载
     */
    public ApplePhone() {
        //默认构造器，不做任何操作，用于验证默认参数设置
        System.out.println("ApplePhone Default Constructor");
    }

    @Override
    public void call() {
        System.out.println("iPhone calls");
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void showName() {
        System.out.println("Public showName: " + name);
    }
}
