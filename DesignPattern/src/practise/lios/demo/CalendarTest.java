package practise.lios.demo;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author liaiguang
 * @created 2020/5/25
 */
public class CalendarTest {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int today = now.getDayOfMonth();

        // 获取本月第一天
        LocalDate dayOfMonth = now.minusDays(today - 1);
        // 获取本月第一天是星期几
        DayOfWeek dayOfWeek = dayOfMonth.getDayOfWeek();
        int week = dayOfWeek.getValue();

        System.out.println("Mon  Tue  Wed  Thu  Fri  Sat  Sun");
        for (int i = 1; i < week; ++i) {
            System.out.print("     ");
        }

        while (dayOfMonth.getMonthValue() == month) {
            if (dayOfMonth.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
            if (dayOfMonth.getDayOfMonth() == today) {
                System.out.printf(" %2d* ",today);
            } else {
                System.out.printf("%3d  ", dayOfMonth.getDayOfMonth());
            }

            dayOfMonth = dayOfMonth.plusDays(1);
        }
    }

}