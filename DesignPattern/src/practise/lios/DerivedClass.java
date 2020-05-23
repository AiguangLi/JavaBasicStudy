package practise.lios;

/**
 * @author liaiguang
 */
public class DerivedClass extends BaseClass {
    private int number = 1;

    @Override
    void show() {
        System.out.println("show called in DerivedClass.show()! number = " + number);
    }

    public DerivedClass(int num) {
        number = num;
        System.out.println("show called in DerivedClass Constructor! number = " + number);
    }

    public static void main(String[] args) {
        DerivedClass po = new DerivedClass(5);
        po.show();
    }
}
