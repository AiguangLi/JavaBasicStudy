package designpatter.lios.factory;

/**
 * @author liaiguang
 */
public class Example{
    /**
     * 抽象工厂方法示例
     * @param args
     */
    public static void main(String[] args) {
        AbstractHumanFactory humanFactory = new HumanFactory();
        YellowHuman yellowHuman = humanFactory.createHuman(YellowHuman.class);
        yellowHuman.getSkinColor();
        yellowHuman.speak();

        WhiteHuman whiteHuman = humanFactory.createHuman(WhiteHuman.class);
        whiteHuman.getSkinColor();
        whiteHuman.speak();

        BlackHuman blackHuman = humanFactory.createHuman(BlackHuman.class);
        blackHuman.getSkinColor();
        blackHuman.speak();
    }
}
