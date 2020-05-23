package designpatter.lios.factory;

/**
 * @author liaiguang
 */
public class WhiteHuman extends AbstractHuman {
    public void getSkinColor() {
        System.out.println("白皮肤");
    }

    public void speak() {
        System.out.println("白种人一般使用单字节说话");
    }
}
