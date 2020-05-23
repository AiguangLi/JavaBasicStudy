package com.lios.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author liaiguang
 * @created 2020/5/23
 */
public class DynamicProxy<T> {
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler) {
        (new BeforeAdvice()).exec();

        T instance = (T) Proxy.newProxyInstance(loader, interfaces, handler);

        return instance;
    }
}