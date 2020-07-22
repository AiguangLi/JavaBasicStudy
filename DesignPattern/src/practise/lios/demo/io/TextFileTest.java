package practise.lios.demo.io;

import practise.lios.demo.Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author liaiguang
 * @date 2020/7/22
 */
public class TextFileTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("张三", "12340.23", 1987, 4, 1);
        staff[1] = new Employee("李四", "12240.23", 1988, 2, 15);
        staff[2] = new Employee("李明儿", "6340.23", 1998, 4, 3);

        try (PrintWriter out =  new PrintWriter("employee.dat", StandardCharsets.UTF_8)) {
            writeData(staff, out);
        }

        try (Scanner in = new Scanner(new FileInputStream("employee.dat"), StandardCharsets.UTF_8)) {
            Employee[] staffOfFile = readData(in);

            for (Employee employee : staffOfFile) {
                System.out.println(employee);
            }
        }

    }

    public static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);

        for (Employee employee : employees) {
            writeEmployee(out, employee);
        }
    }

    public static void writeEmployee(PrintWriter out, Employee employee) {
        out.println(employee.getName() + "|" + employee.getSalary() + "|" + employee.hireDate());
    }

    public static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();  //跳过换行符
        Employee[] staffFromFile = new Employee[n];
        for (int i = 0; i < n; ++i) {
            staffFromFile[i] = readEmployee(in);
        }

        return staffFromFile;
    }

    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        String salary = tokens[1];
        LocalDate hideDate = LocalDate.parse(tokens[2]);

        return new Employee(name, salary, hideDate.getYear(), hideDate.getMonthValue(), hideDate.getDayOfMonth());
    }
}