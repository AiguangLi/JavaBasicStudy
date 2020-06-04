package practise.lios.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author liaiguang
 * @created 2020/6/3
 */
public class AnnotationTest {
    @SimpleAnnotation
    int id;

    @SimpleAnnotation
    String name;

    @SimpleAnnotation
    public String getName() {
        return name;
    }

    @SimpleAnnotation
    public AnnotationTest(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @SimpleAnnotation
    public static void main(String[] args) {
        @SimpleAnnotation
        AnnotationTest test = new AnnotationTest("test1", 1);

        System.out.println(test.getName());

        System.out.println("SimpleAnnotation Methods:");
        for (Method m : AnnotationTest.class.getDeclaredMethods()) {
            SimpleAnnotation simpleAnnotation = m.getAnnotation(SimpleAnnotation.class);
            if (simpleAnnotation != null) {
                System.out.println(m.getName());
            }
        }

        System.out.println("SimpleAnnotation Fields:");
        for (Field f : AnnotationTest.class.getDeclaredFields()) {
            SimpleAnnotation simpleAnnotation = f.getAnnotation(SimpleAnnotation.class);
            if (simpleAnnotation != null) {
                System.out.println(f.getName());
            }
        }

        System.out.println("SimpleAnnotation Constructors:");
        for (Constructor c : AnnotationTest.class.getDeclaredConstructors()) {
            SimpleAnnotation simpleAnnotation = (SimpleAnnotation) c.getAnnotation(SimpleAnnotation.class);
            if (simpleAnnotation != null) {
                System.out.println(c.getName());
            }
        }
    }

}