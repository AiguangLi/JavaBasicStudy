package practise.lios.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @created 2020/6/6
 */
public class LambdaDemo {
    String name;
    public LambdaDemo(String name) {
        this.name = name;
    }
    public static void main(String[] args) {
        String[] names = new String[] {"Lily", "Marry", "Gorge", "James", "Alpha", "Sena"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println("==========compareToIgnoreCase==========");
        System.out.println(Arrays.toString(names));

        Stream<LambdaDemo> stream = Arrays.stream(names).map(LambdaDemo::new);
        List<LambdaDemo> demos = stream.collect(Collectors.toList());
        System.out.println("==========Constructor lambda==========");
        for (LambdaDemo demo: demos) {
            System.out.println(demo.name);
        }

        String[] withSpaceStr = new String[] {" Lily", " Marry ", "Gorge ", "James", "\nAlpha", "Sena"};
        System.out.println("==========trim==========");
        Stream.of(withSpaceStr).sorted(String::compareToIgnoreCase).map(String::trim).map((x) -> (x + ",")).forEach(System.out::print);
        //Arrays.sort(withSpaceStr, String::trim)

        //Stream是泛型，需要使用类型
        Integer[] numbers = new Integer[]{1, 2, 3, 4, 5, 6};
        System.out.println("==========sum==========");
        System.out.println(Stream.of(numbers).mapToInt(x -> x).sum());
    }

}