package com.lios.factory;

public abstract class AbstractHumanFactory {
    /**
     * 创建人类的抽象方法
     * @param clazz
     * @param <T>
     * @return
     */
    public abstract <T extends AbstractHuman> T createHuman(Class<T> clazz);
}
