package practise.lios.demo.io;

import practise.lios.demo.Employee;

import java.io.*;

/**
 * @author liaiguang
 * @date 2020/7/22
 */
public class RandomAccessFileTest {
    final static int SALARY_SIZE = 20;
    final static int NAME_SIZE = 64;
    /**
     * 写入时一个char占两个字节
     */
    final static int EMPLOYEE_RECORD_SIZE = SALARY_SIZE * 2 + NAME_SIZE * 2 + 12;
    public static void main(String[] args) throws IOException {

        Employee[] staff = new Employee[3];

        staff[0] = new Employee("张三", "12340.23", 1987, 4, 1);
        staff[1] = new Employee("李四", "12240.23", 1988, 2, 15);
        staff[2] = new Employee("李明儿", "6340.23", 1998, 4, 3);

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"))) {
            for (Employee employee: staff) {
                writeData(out, employee);
            }
        }

        try (RandomAccessFile in = new RandomAccessFile("employee.dat", "r")) {
            int n = (int)(in.length() / EMPLOYEE_RECORD_SIZE);

            Employee[] employees = new Employee[n];
            for (int i = n - 1; i >= 0; --i) {
                in.seek(i * EMPLOYEE_RECORD_SIZE);
                employees[i] = readData(in);
            }

            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }

    }

    public static void writeData(DataOutput out, Employee employee) throws IOException {
        DataIO.writeFixedString(employee.getName(), NAME_SIZE, out);
        DataIO.writeFixedString(employee.getSalary(), SALARY_SIZE, out);
        out.writeInt(employee.getHireDate().getYear());
        out.writeInt(employee.getHireDate().getMonthValue());
        out.writeInt(employee.getHireDate().getDayOfMonth());
    }

    public static Employee readData(DataInput input) throws IOException {
        String name = DataIO.readFixedString(NAME_SIZE, input);
        String salary = DataIO.readFixedString(SALARY_SIZE, input);
        int year = input.readInt();
        int month = input.readInt();
        int day = input.readInt();

        return new Employee(name, salary, year, month, day);
    }
}