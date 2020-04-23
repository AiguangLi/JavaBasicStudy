package com.lios;

import com.lios.models.AndroidPhone;
import com.lios.models.ApplePhone;
import com.lios.models.Light;
import com.lios.models.Phone;
import java.util.Date;

/**
 * @author liaiguang
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());

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
    }

    public static void callUsingPhone(Phone phone) {
        phone.call();
    }

    public static void changeApplePhoneName(ApplePhone phone, String newName) {
        phone.setName(newName);
        System.out.println(phone.toString());
    }
}
