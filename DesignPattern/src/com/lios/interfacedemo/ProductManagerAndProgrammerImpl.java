package com.lios.interfacedemo;

/**
 * @author liaiguang
 */
public class ProductManagerAndProgrammerImpl extends Person implements ProgrammerService {
    @Override
    public void writeCode() {
        System.out.println("我是产品经理，我还会写代码！");
    }

    public void designProduct() {
        System.out.println("我是产品经理，我会设计产品！");
    }
}
