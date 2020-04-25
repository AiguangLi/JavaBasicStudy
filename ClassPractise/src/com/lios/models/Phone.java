package com.lios.models;

/**
 * @author liaiguang
 */
public class Phone {
    protected String name;
    protected float memory;
    protected float diskSize;

    public Phone(String name, float memory, float diskSize) {
        //通过this调用其他构造方法
        this(name, memory);
        this.diskSize = diskSize;
    }

    public Phone(String name, float memory) {
        this.name = name;
        this.memory = memory;
    }

    public Phone() {
        //默认构造器，不做任何操作，用于验证默认参数设置
    }

    Phone(Phone anotherPhone) {
        this.name = anotherPhone.name;
        this.diskSize = anotherPhone.diskSize;
        this.memory = anotherPhone.memory;

        System.out.println("Get Phone in package Constructor");
    }

    public static Phone getPhoneCopy(Phone phoneToCopy) {
        System.out.println("Get Phone Copy using package Constructor");
        return new Phone(phoneToCopy);
    }

    //默认参数设置块
    {
        name = "Pixel 2";
        memory = 2.0f;
        diskSize = 64;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", memory=" + memory +
                ", diskSize=" + diskSize +
                '}';
    }

    public void call() {
        System.out.println("Phone calls!");
    }
}
