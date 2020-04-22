package com.lios.models;

/**
 * @author liaiguang
 */
public class Phone {
    protected String name;
    protected float memory;
    protected float diskSize;

    public Phone(String name, float memory, float diskSize) {
        this.name = name;
        this.memory = memory;
        this.diskSize = diskSize;
    }

    public Phone() {
        //默认构造器，不做任何操作，用于验证默认参数设置
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
