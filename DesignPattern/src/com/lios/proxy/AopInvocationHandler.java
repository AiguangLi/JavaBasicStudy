package com.lios.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理反射处理
 * @author liaiguang
 * @created 2020/5/23
 */
public class AopInvocationHandler implements InvocationHandler {
    /**
     * 被代理对象
     */
    private Object proxyTarget = null;

    /**
     * 构造方法传入被代理对象
     * @param proxyTarget：被代理对象
     */
    public AopInvocationHandler(Object proxyTarget) {
        this.proxyTarget = proxyTarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.toString() + "准备执行");
        Object obj =  method.invoke(proxyTarget, args);
        System.out.println(method.toString() + "执行完成");
        return obj;
    }
}