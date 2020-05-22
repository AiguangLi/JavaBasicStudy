package com.lios.factory;

/**
 * @author liaiguang
 */
public class HumanFactory extends AbstractHumanFactory {

    @Override
    public <T extends AbstractHuman> T createHuman(Class<T> clazz) {
        T human = null;
        try {
            human = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("创建人类失败：" + e.toString());
        }

        return human;
    }
}
