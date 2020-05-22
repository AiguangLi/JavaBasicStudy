package com.lios.interfacedemo;

/**
 * @author liaiguang
 */
public class ProgrammerJob {
    void needProgrammer(ProgrammerService programmer) {
        programmer.writeCode();
    }

    public static void main(String[] args) {
        ProgrammerJob job = new ProgrammerJob();
        //来了一个程序员
        ProgrammerImpl programmer = new ProgrammerImpl();
        //人事MM可以用needProgrammer处理
        job.needProgrammer(programmer);
        //来了一个会写代码的产品经理
        ProductManagerAndProgrammerImpl productManagerAndProgrammer = new ProductManagerAndProgrammerImpl();
        //人事MM还是可以用needProgrammer处理
        job.needProgrammer(productManagerAndProgrammer);
    }
}
