package practise.lios.demo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author liaiguang
 * @created 2020/5/31
 */
public class Manager extends Employee {
    private final BigDecimal bonus;
    private Employee secretary;

    public Manager(String name, String salary, int year, int month, int day, String bonus) {
        //调用super的构造函数初始化父类已有的成员变量
        super(name, salary, year, month, day);
        this.bonus = new BigDecimal(bonus);
    }

    @Override
    public String getSalary() {
        //salary是父类私有成员，无法访问，需要使用super的get方法
        BigDecimal baseSalary = new BigDecimal(super.getSalary());
        BigDecimal salaryWithBonus = baseSalary.add(bonus);

        return salaryWithBonus.toString();
    }

    public BigDecimal getBonus() {
        return bonus;
    }


    @Override
    public boolean equals(Object obj) {
        if (! super.equals(obj)) {
            return false;
        }

        Manager other = (Manager) obj;

        return other.bonus.equals(bonus);
    }

    @Override
    public int hashCode() {
        //默认从对象的存储地址中得出散列码（String类型因为是静态分配的，相同的字符串内容的String对象hashCode相同）
        //两个相等的对象要求返回相同的hashCode
        return Objects.hash(super.hashCode(), bonus);
    }

    @Override
    public String toString() {
        return super.toString() + "\nbonus: " + bonus;
    }

    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }
}