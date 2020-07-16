package practise.lios.demo.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @date 2020/7/16
 */
public class StreamSummary {
    public static void main(String[] args) {
        //从Collection中产生流
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            strings.add("string" + i);
        }

        Stream<String> stringStream = strings.stream();
        show("stringStream", stringStream);

        //使用generate产生流
        Stream<Integer> integerStream = Stream.generate(() -> (int) (Math.random() * 100));
        show("integerStream", integerStream);
        //使用iterate产生序列
        Stream<Integer> integerStream1 = Stream.iterate(0, n -> n + 1);
        show("integerStream1", integerStream1);

        //使用of方法创建流
        Stream<String> stringStream1 = Stream.of("Bob", "Mark", "Alice", "Edd");
        show("stringStream1", stringStream1);

        //正则表达式产生流
        String sentences = "I had a dog whose name was Bingo. B, I, N, G, O, Bingo was his name, Oh!";
        Stream<String> words = Pattern.compile("\\PL+").splitAsStream(sentences);
        show("PatterStream", words);

        Stream<String> words1 = Pattern.compile("\\PL+").splitAsStream(sentences).filter(s -> s.length() > 2);
        show("PatterStream1", words1);

        Stream<String> words2 = Pattern.compile("\\PL+").splitAsStream(sentences).map(String::toUpperCase);
        show("PatterStream2", words2);

        Stream<String> words3 = Pattern.compile("\\PL+").splitAsStream(sentences).distinct();
        show("PatterStream3", words3);

        //按长度对单词进行排序
        Stream<String> words4 = Pattern.compile("\\PL+").splitAsStream(sentences).sorted(Comparator.comparing(String::length));
        show("PatterStream4", words4);

        //按长度对单词进行排序后反序
        Stream<String> words5 = Pattern.compile("\\PL+").splitAsStream(sentences).sorted(Comparator.comparing(String::length).reversed());
        show("PatterStream4", words5);
    }

    public static<T> void show(String title, Stream<T> stream) {
        final int size = 10;
        List<T> array = stream.limit(size).collect(Collectors.toList());
        System.out.println(title + ": ");
        for (int i = 0; i < array.size(); ++i) {
            if (i > 0) {
                System.out.print(", ");
            }
            if (i < array.size()) {
                System.out.print(array.get(i));
            } else {
                System.out.print("...");
            }
        }
        System.out.println();
    }

}