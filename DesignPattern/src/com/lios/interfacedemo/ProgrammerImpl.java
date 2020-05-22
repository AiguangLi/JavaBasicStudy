package com.lios.interfacedemo;

/**
 * @author liaiguang
 */
public class ProgrammerImpl extends Person implements ProgrammerService{

    @Override
    public void writeCode() {
        System.out.println("我是程序员，我会写代码！");
    }
}