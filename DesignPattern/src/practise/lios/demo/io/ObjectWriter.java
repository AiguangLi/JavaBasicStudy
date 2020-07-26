package practise.lios.demo.io;

import practise.lios.demo.Employee;
import practise.lios.demo.Manager;

import java.io.*;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @date 2020/7/25
 */
public class ObjectWriter {
    public static void main(String[] args) throws IOException {
        Employee harry = new Employee("Harry", "12932.23", 1989, 4, 3);
        Manager bob = new Manager("Bob", "18000", 1986, 3, 1, "3000");
        bob.setSecretary(harry);

        Manager tony = new Manager("Tony", "16000", 1987, 3, 2, "2000");
        tony.setSecretary(harry);

        Employee[] staff = new Employee[3];
        staff[0] = harry;
        staff[1] = bob;
        staff[2] = tony;

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat"))) {
            out.writeObject(staff);
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("employee.dat"))) {
            Employee[] staffFromFile = (Employee[]) input.readObject();

            staffFromFile[1].raiseSalary("1000");

            Stream.of(staffFromFile).forEach(System.out::println);
            System.out.println(((Manager)staffFromFile[1]).getSecretary());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}