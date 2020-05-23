package practise.lios.demo;

import practise.lios.models.Fruit;

import java.util.*;

/**
 * @author liaiguang
 */
public class CollectionDemo {
    int number;
    public CollectionDemo(int number) {
        this.number = number;
    }

    public Apple getApple(String name, float weight, boolean isYummy) {
        return new Apple(name, weight, isYummy);
    }

    public Orange getOrange(String name, float weight, boolean isYummy, String color) {
        return new Orange(name, weight, isYummy, color);
    }

    class Apple extends Fruit {
        String name;
        float weight;
        boolean isYummy;

        Apple(String name, float weight, boolean isYummy) {
            this.name = name;
            this.weight = weight;
            this.isYummy = isYummy;
        }

        @Override
        public void show() {
            System.out.println("Apple{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", isYummy=" + isYummy +
                    //内部类可以捕获外部类的成员
                    ", Out class id = " + number +
                    '}');
        }
    }

    class Orange extends Fruit{
        String name;
        float weight;
        boolean isYummy;
        String color;

        Orange(String name, float weight, boolean isYummy, String color) {
            this.name = name;
            this.weight = weight;
            this.isYummy = isYummy;
            this.color = color;
        }

        public void increment() {
            number++;
        }

        @Override
        public void show() {
            System.out.println("Orange{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", isYummy=" + isYummy +
                    ", Color=" + color +
                    //内部类可以捕获外部类的成员
                    ", Out class id = " + number +
                    '}');
        }
    }

    public void showNumber() {
        System.out.println("CollectionDemo number = "  + number);
    }

    public static void main(String[] args) {
        ArrayList<Fruit> list = new ArrayList<>();
        CollectionDemo demo = new CollectionDemo(123);
        Apple apple = demo.getApple("富士", 0.5f, true);
        list.add(apple);
        Orange orange = demo.getOrange("脐橙", 0.1f, true, "#561232");
        list.add(orange);

        for(Fruit fruit: list) {
            fruit.show();
        }

        demo.showNumber();
        //通过内部类捕获外围类的变量，可以实现闭包
        orange.increment();
        demo.showNumber();
    }

}
