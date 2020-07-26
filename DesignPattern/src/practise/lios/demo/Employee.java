package practise.lios.demo;

import practise.lios.demo.io.SerialCloneable;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * @author liaiguang
 * @created 2020/5/25
 */
public class Employee extends SerialCloneable implements Comparable<Employee> {
    private final String name;
    private BigDecimal salary;
    private final LocalDate hireDate;
    private static int nextId = 1;
    private int id = 0;
    private Date leaveDate;

//    public Employee() {
//        this.name = "-";
//        this.salary = new BigDecimal("0.00");
//        hireDate = LocalDate.of(1970, 1, 1);
//    }

    public Employee(String name, String salary, int year, int month, int day) {
        this.name = name;
        this.salary = new BigDecimal(salary);
        hireDate = LocalDate.of(year, month, day);
        leaveDate = new Date();
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String hireDate() {
        return hireDate.toString();
    }

    public int getId() {
        return id;
    }

    public void raiseSalary(String salaryAdded) {
        //BigDecimal每次add返回的是一个新对象，因此不影响拷贝操作（拷贝对象的修改值不影响原对象的值）
        salary = salary.add(new BigDecimal(salaryAdded));
    }

    public void modifyLeaveDate(int year, int month, int day) {
        Date newLeaveDate = new GregorianCalendar(year, month - 1, day).getTime();

        leaveDate.setTime(newLeaveDate.getTime());
    }

    /**
     * equals方法要求保持：
     * 自反性：(自己=自己)
     * 对称性：(y.equals(x)与x.equals(y)相同
     * 传递性：y.equals(x), z.equals(y)如果都为true，则z.equals(x)也为true
     * 一致性：如果x和y没有发生变化，则x.equals(y)结果是相同的。
     * 非空引用，x.equals(null)应返回false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        //要求两个类类名完全相同
        if (obj.getClass() != getClass()) {
            return false;
        }

        Employee other = (Employee) obj;
        return other.name.equals(name) && other.salary.equals(salary) && other.hireDate.equals(hireDate);
    }

    @Override
    public int hashCode() {
        //默认从对象的存储地址中得出散列码（String类型因为是静态分配的，相同的字符串内容的String对象hashCode相同）
        //两个相等的对象要求返回相同的hashCode
        return Objects.hash(name, salary, hireDate);
    }

//    @Override
//    public Employee clone() throws CloneNotSupportedException {
//        //浅拷贝会导致leaveDate被克隆对象修改
//        //谨慎使用clone方法，因为子类行为无法预测
//        //return (Employee)super.clone();
//        Employee cloned = (Employee) super.clone();
//
//        //深拷贝需要拷贝非基本类型，以及非final对象
//        cloned.leaveDate = (Date) leaveDate.clone();
//
//        return cloned;
//    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, CloneNotSupportedException {
        var staff = new ArrayList<Employee>(4);
        staff.add(new Employee("张三", "3421.12", 2020, 1, 1));
        staff.add(new Employee("李四", "6621.12", 2010, 4, 12));
        staff.add(new Employee("王五", "19921.22", 2005, 4, 12));
        staff.add(new Manager("经理", "15000", 2001, 4, 5, "1000.00"));

        staff.get(1).raiseSalary("1000.23");
        staff.get(3).raiseSalary("500");
        System.out.println("============Before Sort=============");
        for (Employee e : staff) {
            //e.setId();
            Employee.showEmployee(e);
        }

        //数组不可以强制转换，但单个元素可以转换
        Object[] employees = staff.toArray();
        Arrays.sort(employees);
        System.out.println("===============After Sort==============");
        for (Object e : employees) {
            Employee.showEmployee((Employee) e);
        }

        Employee[] employeeArray = new Employee[staff.size()];
        employeeArray = staff.toArray(employeeArray);
        //lambda实现排序，Arrays.sort需要能够推断出比较对象的类型，因此需要将ArrayList<Employee>转成Employee[]。
        Arrays.sort(employeeArray, (Employee e1, Employee e2) -> e1.salary.compareTo(e2.salary));
        System.out.println("==============After Lambda Sort=============");
        for (Object e : employeeArray) {
            Employee.showEmployee((Employee) e);
        }

        System.out.println(staff.get(1).equals(staff.get(2)));

        //按值传递影响成员
        Employee.raiseEmployeeSalary(staff.get(2), "1024.01");
        Employee.showEmployee(staff.get(2));

        System.out.println("Before Swap: ");
        Employee.showEmployee(staff.get(0));
        Employee.showEmployee(staff.get(1));

        System.out.println("After Swap: ");
        // 交换实际没有发生，说明对象其实是按值传递的
        Employee.swap(staff.get(0), staff.get(1));
        Employee.showEmployee(staff.get(0));
        Employee.showEmployee(staff.get(1));

        //反射
        Class<Employee> employeeClass = Employee.class;
        //调用指定构造函数需要指定getConstructor的参数的class
        Employee reflectEmployee =
                employeeClass
                        .getConstructor(String.class, String.class, int.class, int.class, int.class)
                        .newInstance("王可", "1000", 2010, 1, 1);
        showEmployee(reflectEmployee);

        // 不推荐使用forName的反射
        try {
            Class<Employee> employeeNameClass = (Class<Employee>) Class.forName("practise.lios.demo.Employee");
            Employee reflectNameEmployee = (Employee) employeeNameClass.getConstructor(String.class, String.class, int.class, int.class, int.class)
                    .newInstance("刘明", "1000", 2012, 1, 1);
            showEmployee(reflectNameEmployee);
        } catch (ClassNotFoundException | InstantiationException e) {
            System.out.println(e.toString());
        }


        Employee clonedEmployee = (Employee) staff.get(2).clone();
        System.out.println("Before Clone Operation:");
        showEmployee(staff.get(2));
        clonedEmployee.modifyLeaveDate(2020, 2, 1);
        System.out.println("After Clone Operation:");
        showEmployee(staff.get(2));
        System.out.println("Cloned Employee:");
        showEmployee(clonedEmployee);

    }

    private static <T> void swap(T a, T b) {
        T temp = a;
        a = b;
        b = temp;
    }

    /**
     * 对象为值传递，但是在方法内部改变对象成员变量会影响对象的成员变量
     *
     * @param employee:    Employee
     * @param addedSalary: String
     */
    private static void raiseEmployeeSalary(Employee employee, String addedSalary) {
        employee.salary = employee.salary.add(new BigDecimal(addedSalary));
    }

    private static void showEmployee(Employee employee) {
        System.out.println(employee.toString());
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "\nhashCode: " + hashCode() +
                "\nid: " + id +
                "\nname: " + name +
                "\nhireDate: " + hireDate +
                "\nsalary: " + getSalary() +
                "\nleaveDate: " + leaveDate;
    }

    public boolean sameName(Employee anotherEmployee) {
        //同一类中可以访问相同类的私有成员，如这里的anotherEmployee.name
        return this.name.equals(anotherEmployee.name);
    }

    //使用final不让子类覆盖，以免修改行为导致无法满足里氏替换原则
    @Override
    public final int compareTo(Employee e) {
        return salary.compareTo(e.salary);
    }
}