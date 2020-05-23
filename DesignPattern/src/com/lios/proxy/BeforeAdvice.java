package com.lios.proxy;

/**
 * @author liaiguang
 * @created 2020/5/23
 */
public class BeforeAdvice implements IAdvice {
    @Override
    public void exec() {
        System.out.println("前置代理通知已执行");
    }
}