package practise.lios.demo;

/**
 * 泛型演示代码
 * @author liaiguang
 * @date 2020/6/13
 */
public class GenericDemo {
    public static void main(String[] args) {
        Manager ceo = new Manager("Mark", "10029.23", 2010, 1, 1, "10000");
        Manager cto = new Manager("Bony", "8000.87", 2012, 4, 5, "5000");
        ArrayAlgorithm.Pair<Manager> buddies = new ArrayAlgorithm.Pair<>(ceo, cto);
        printBuddies(buddies);

        Manager[] managers = {ceo, cto};
        ArrayAlgorithm.Pair<Employee> minMaxResult = new ArrayAlgorithm.Pair<>();

        minMaxBonus(managers, minMaxResult);
        System.out.println("first: " + minMaxResult.getFirst().getName() + ", second: "
                + minMaxResult.getSecond().getName());

        ArrayAlgorithm.Pair<Employee> maxMinResult = new ArrayAlgorithm.Pair<>();
        maxMinBonus(managers, maxMinResult);
        System.out.println("After swap, first: " + maxMinResult.getFirst().getName() + ", second: "
                + maxMinResult.getSecond().getName());

    }

    public static void printBuddies(ArrayAlgorithm.Pair<? extends Employee> pair) {
        Employee first = pair.getFirst();
        Employee second = pair.getSecond();

        System.out.println("first: " + first.getName() + ", second: " + second.getName() + " arr buddies");
    }

    public static void minMaxBonus(Manager[] managers, ArrayAlgorithm.Pair<? super Manager> pair) {
        Manager min = managers[0];
        Manager max = managers[0];

        for (Manager v: managers) {
            if (v.getBonus().compareTo(min.getBonus()) < 0) {
                min = v;
            }

            if (v.getBonus().compareTo(max.getBonus()) > 0) {
                max = v;
            }
        }
        //子类转父类OK，但如果调用ArrayAlgorithm.minMax方法返回的Pair.getFirst()则不行
        // 因为类型擦除后，编译器不知道? super Manager并不知道具体的类型是什么
        pair.setFirst(min);
        pair.setSecond(max);

        //泛型返回的是Employee,与? super Manager未必匹配，因此无法set
        //ArrayAlgorithm.Pair<Employee> minMax = ArrayAlgorithm.minMax(managers);
        //pair.setFirst(minMax.getFirst());  //无法编译
        //pair.setSecond(minMax.getSecond());
    }

    public static void maxMinBonus(Manager[] managers, ArrayAlgorithm.Pair<? super  Manager> pair) {
        minMaxBonus(managers, pair);
        swap(pair);
    }

    private static void swap(ArrayAlgorithm.Pair<?> pair) {
        //通过swapHelper捕获通配符类型
        swapHelper(pair);
    }

    private static <T> void swapHelper(ArrayAlgorithm.Pair<T> pair) {
        T t = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(t);
    }

    private static boolean hasNull(ArrayAlgorithm.Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }
}