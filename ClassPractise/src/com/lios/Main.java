package com.lios;

import com.lios.models.Phone;

/**
 * @author liaiguang
 */
public class Main {
    public static void main(String[] args) {
        Phone iPhone = new Phone("iPhone", 2.0f, 16);
        Phone defaultPhone = new Phone();
        System.out.println(iPhone.toString());
        System.out.println(defaultPhone.toString());
    }
}
