package practise.lios.demo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author liaiguang
 * @created 2020/5/25
 */
public class Employee {
    private String name;
    private BigDecimal salary;
    private LocalDate hireDate;

    public Employee(String _name, String _salary, int _year, int _month, int _day) {
        name = _name;
        salary = new BigDecimal(_salary);
        hireDate = LocalDate.of(_year, _month, _day);
    }

    public String getName() {
        return name;
    }

    public String getSalary() {
        return salary.toString();
    }

    public String hireDate() {
        return hireDate.toString();
    }

    public void raiseSalary(String salaryAdded) {
        salary = salary.add(new BigDecimal(salaryAdded));
    }

    public static void main(String[] args) {
        Employee employee1 = new Employee("张三", "3421.12", 2020, 1, 1);
        Employee employee2 = new Employee("李四", "6621.12", 2010, 4, 12);
        Employee employee3 = new Employee("王五", "19921.22", 2005, 4, 12);

        employee1.raiseSalary("1000.23");

        Employee.showEmployee(employee1);
        Employee.showEmployee(employee2);
        Employee.showEmployee(employee3);

        System.out.println(employee1.equals(employee2));
    }

    private static void showEmployee(Employee employee) {
        String result = "name :" +
                employee.getName() +
                "\n" + "hireDate: " +
                employee.hireDate() +
                "\n" +
                "salary: " +
                employee.getSalary();
        System.out.println(result);
    }

    public boolean equals(Employee anotherEmployee) {
        //同一类中可以访问相同类的私有成员，如这里的anotherEmployee.name
        return this.name.equals(anotherEmployee.name);
    }
}