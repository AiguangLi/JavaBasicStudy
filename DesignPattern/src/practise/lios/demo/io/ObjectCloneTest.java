package practise.lios.demo.io;

import practise.lios.demo.Employee;

/**
 * @author liaiguang
 * @date 2020/7/25
 */
public class ObjectCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee harry = new Employee("Harry", "12932.23", 1989, 4, 3);
        Employee harryClone = (Employee) harry.clone();
        harry.raiseSalary("1000");

        System.out.println(harry);
        System.out.println(harryClone);
    }
}