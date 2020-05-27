package practise.lios.demo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author liaiguang
 * @created 2020/5/25
 */
public class Employee<T> {
    private final String name;
    private BigDecimal salary;
    private final LocalDate hireDate;
    private static int nextId = 1;
    private int id = 0;

    public Employee(String _name, String _salary, int _year, int _month, int _day) {
        name = _name;
        salary = new BigDecimal(_salary);
        hireDate = LocalDate.of(_year, _month, _day);
    }

    {
        //初始化的时候先创建了对象的内存地址，然后再调用块代码，之后才是调用构造函数初始化其他变量
        //因此在块中可以获取this指向当前对象
        id = nextId;
        nextId++;
    }

//    public void setId() {
//        this.id = nextId;
//        nextId ++;
//    }

    public String getName() {
        return name;
    }

    public String getSalary() {
        return salary.toString();
    }

    public String hireDate() {
        return hireDate.toString();
    }

    public int getId() {
        return id;
    }

    public void raiseSalary(String salaryAdded) {
        salary = salary.add(new BigDecimal(salaryAdded));
    }

    public static void main(String[] args) {
        var staff = new Employee[3];
        staff[0] = new Employee("张三", "3421.12", 2020, 1, 1);
        staff[1] = new Employee("李四", "6621.12", 2010, 4, 12);
        staff[2] = new Employee("王五", "19921.22", 2005, 4, 12);

        staff[1].raiseSalary("1000.23");
        for (Employee e : staff) {
            //e.setId();
            Employee.showEmployee(e);
        }

        System.out.println(staff[1].equals(staff[2]));

        //按值传递影响成员
        Employee.raiseEmployeeSalary(staff[2], "1024.01");
        Employee.showEmployee(staff[2]);

        System.out.println("Before Swap: ");
        Employee.showEmployee(staff[0]);
        Employee.showEmployee(staff[1]);

        System.out.println("After Swap: ");
        // 交换实际没有发生，说明对象其实是按值传递的
        Employee.swap(staff[0], staff[1]);
        Employee.showEmployee(staff[0]);
        Employee.showEmployee(staff[1]);
    }

    private static <T> void swap(T a, T b) {
        T temp = a;
        a = b;
        b = temp;
    }

    /**
     * 对象为值传递，但是在方法内部改变对象成员变量会影响对象的成员变量
     * @param employee: Employee
     * @param addedSalary: String
     */
    private static void raiseEmployeeSalary(Employee employee, String addedSalary) {
        employee.salary = employee.salary.add(new BigDecimal(addedSalary));
    }

    private static void showEmployee(Employee employee) {
        String result = "id: " + employee.id +
                "\nname: " + employee.name +
                "\nhireDate: " + employee.hireDate +
                "\nsalary: " + employee.salary;
        System.out.println(result);
    }

    public boolean equals(Employee anotherEmployee) {
        //同一类中可以访问相同类的私有成员，如这里的anotherEmployee.name
        return this.name.equals(anotherEmployee.name);
    }
}