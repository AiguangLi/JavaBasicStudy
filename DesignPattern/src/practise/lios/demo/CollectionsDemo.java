package practise.lios.demo;

import java.util.*;

/**
 * @author liaiguang
 * @date 2020/6/20
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        HashSet<String> words = new HashSet<>();
        TreeSet<String> treeSetWords = new TreeSet<>();
        long totalTime = 0;
        long totalTimeOfTreeSet = 0;


        try (Scanner in = new Scanner(System.in)) {
            while(in.hasNext()) {
                String word = in.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;

                callTime = System.currentTimeMillis();
                treeSetWords.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTimeOfTreeSet += callTime;
            }
        }

        System.out.println("...HashSet...");
        Iterator<String> iter = words.iterator();
        for(int i = 1; i <= 20 && iter.hasNext(); ++i) {
            System.out.println(iter.next());
        }
        System.out.println("...");
        System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds. ");

        System.out.println("...TreeSet...");
        Iterator<String> iterOfTree = treeSetWords.iterator();
        for(int i = 1; i <= 20 && iterOfTree.hasNext(); ++i) {
            System.out.println(iterOfTree.next());
        }
        System.out.println("...");
        System.out.println(treeSetWords.size() + " distinct words. " + totalTimeOfTreeSet + " milliseconds. ");

        TreeSet<Employee> employees = new TreeSet<>();
        employees.add(new Employee("彰武", "11023.23", 1980, 2,3));
        employees.add(new Employee("李可", "18023.23", 1983, 4,3));
        employees.add(new Employee("刘明", "8023.23", 1990, 4,8));
        employees.add(new Employee("王达理", "28023.23", 1963, 8,3));
        System.out.println(employees);

        //设定排序规则
        TreeSet<Employee> sortedEmployeesBySalary = new TreeSet<>(Comparator.comparing(Employee::hireDate));
        sortedEmployeesBySalary.addAll(employees);
        System.out.println(sortedEmployeesBySalary);

        //优先队列适合任务调度，排序顺序越小优先级越高
        PriorityQueue<Employee> employeePriorityQueue = new PriorityQueue<>(employees);
        System.out.println("...Iterating over elements...");
        for (Employee employee : employeePriorityQueue) {
            System.out.println(employee);
        }

        System.out.println("...Removing elements...");
        while(! employeePriorityQueue.isEmpty()) {
            System.out.println(employeePriorityQueue.remove());
        }

        //BitSet用于检测素数个数
        System.out.println("...BitSet Demo...");
        int maxNum = 2000000;
        long start = System.currentTimeMillis();
        BitSet bitset = new BitSet(maxNum + 1);
        int count = 0;
        int i;
        for (i = 2; i <= maxNum; ++i) {
            bitset.set(i);
        }
        i = 2;
        while (i * i <= maxNum) {
            if (bitset.get(i)) {
                count++;
                int k = 2 * i;
                while (k <= maxNum) {
                    bitset.clear(k);
                    k += i;
                }
            }
            i++;
        }

        while (i <= maxNum) {
            if (bitset.get(i)) {
                count++;
            }
            i++;
        }

        long end = System.currentTimeMillis();
        System.out.println(count + " primes. ");
        System.out.println((end - start) + " milliseconds.");
    }
}