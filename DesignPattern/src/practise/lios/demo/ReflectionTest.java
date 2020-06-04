package practise.lios.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author liaiguang
 * @created 2020/6/2
 */
public class ReflectionTest {
    public static void main(String[] args) {
        Class<Employee> employeeClass = Employee.class;
        Class<? super Employee> superClass = employeeClass.getSuperclass();
        String modifiers = Modifier.toString(employeeClass.getModifiers());
        if (modifiers.length() > 0) {
            System.out.print(modifiers + " ");
        }
        if (superClass != null && superClass != Object.class) {
            System.out.print(" extends " + superClass.getName());
        }
        System.out.print("class " + employeeClass.getName());
        System.out.print("\n{\n");

        printConstructors(employeeClass);
        System.out.println();
        printMethods(employeeClass);
        System.out.println();
        printFields(employeeClass);
    }

    static void printConstructors(Class clazz) {
        //返回公共构造函数
        //getDeclaredConstructors返回全部声明的构造函数
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors) {
            String name = c.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(c.getName() + "(");

            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; ++ j) {
                if (j > 0) {
                    System.out.print(", ");
                }

                System.out.print(paramTypes[j].getName());
            }
        }

        System.out.println(");");
    }

    static void printMethods(Class clazz) {
        //返回类的公共方法（含父类）
        //getDeclaredMethods返回全部声明的方法
        Method[] methods = clazz.getMethods();

        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();

            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }

            System.out.println(retType.getName() + " " + name + "(");

            Class[] paramTypes = m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; ++ j) {
                if (j > 0) {
                    System.out.print(", ");
                }

                System.out.print(paramTypes[j].getName());
            }

            System.out.println(");");
        }
    }

    static void printFields(Class clazz) {
        //返回公共字段（含父类）
        Field[] fields = clazz.getFields();

        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();

            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }

            System.out.println(type.getName() + " " + name + ";");
        }
    }
}