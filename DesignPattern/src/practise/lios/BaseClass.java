package practise.lios;

/**
 * @author liaiguang
 */
public class BaseClass {
    void show() {
        System.out.println("show called in BaseClass.show()!");
    }

    public BaseClass() {
        System.out.println("Constructor in BaseClass!");
        // 构造器中调用多态方法会导致问题
        show();
    }
}
