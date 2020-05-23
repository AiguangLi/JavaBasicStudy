package practise.lios.models;

/**
 * @author liaiguang
 */
public class AndroidPhone extends Phone {
    public AndroidPhone(String name, float memory, float diskSize) {
        super(name, memory, diskSize);
    }

    public AndroidPhone() {
        //默认构造器，不做任何操作，用于验证默认参数设置
        System.out.println("AndroidPhone Default Constructor");
    }

    @Override
    public void call() {
        System.out.println("Android Phone calls");
    }
}
