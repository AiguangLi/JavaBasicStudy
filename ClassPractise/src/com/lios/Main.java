package com.lios;

import com.lios.models.*;
import com.lios.util.Range;
import java.util.Date;

/**
 * @author liaiguang
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());

        ApplePhone defaultApplePhone = new ApplePhone();
        AndroidPhone defaultAndroidPhone = new AndroidPhone();
        // 以下代码会报错，因为提升到基类后，showName方法是私有的，编译器会检测出私有方法被调用
        // Phone applePhone = new ApplePhone();
        // applePhone.showName();
        // 改成正常的子类后，可以调用。但是不建议重写基类私有方法。
        ApplePhone applePhone = new ApplePhone();
        applePhone.showName();

        ApplePhone iPhone = new ApplePhone("iPhone", 2.0f, 16);
        AndroidPhone androidPhone = new AndroidPhone("Huawei P40", 6.0f, 256);
        Phone defaultPhone = new Phone();
        System.out.println(iPhone.toString());
        System.out.println(defaultPhone.toString());

        //多态演示
        callUsingPhone(iPhone);
        callUsingPhone(androidPhone);
        callUsingPhone(defaultPhone);

        //方法中传递的是引用，会直接修改对象的属性
        changeApplePhoneName(iPhone, "iPhone X");
        System.out.println(iPhone.toString());

        //类封装方法调用
        Light light = new Light();
        light.on();
        light.brighten();
        light.dim();
        light.off();

        integerObjCompare();

        try {
            int[] array = Range.range(10, 20, 2);
            for (int i: array) {
                System.out.println("array: " + i);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Book bookNormal = new Book(true);
        bookNormal.checkIn();

        Book bookAbnormal = new Book(true);
        System.gc();

        testPackageConstructor(iPhone);

        // 协变示例
        PhoneStore store = new PhoneStore(androidPhone);
        store.show();
        store.call();
        store.changePhone();
        store.show();
        store.call();
    }

    public static void callUsingPhone(Phone phone) {
        phone.call();
    }

    public static void changeApplePhoneName(ApplePhone phone, String newName) {
        phone.setName(newName);
        System.out.println(phone.toString());
    }

    public static void integerObjCompare() {
        Integer n1 = 47;
        Integer n2 = 47;
        String n3 = "2000";
        String n4 = "2000";
        //Integer内部有IntegerCache，范围[-128, 127]，这个范围的Integer对象相同值是共享对象的
        System.out.println(n1.equals(n2));
        System.out.println(n3.equals(n4));
    }

    public static void testPackageConstructor(Phone phone) {
        //无法访问没有访问修饰符的类构造器，因为只能在相同的包里访问
        //Phone defaultPackagePhone = new Phone(phone);
        //通过这种方式可以隐藏某些构造方法不被外部访问，而只能通过别的方式访问
        Phone copiedPhone = Phone.getPhoneCopy(phone);
    }
}
