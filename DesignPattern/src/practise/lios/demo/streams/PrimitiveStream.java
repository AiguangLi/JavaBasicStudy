package practise.lios.demo.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @date 2020/7/14
 */
public class PrimitiveStream {
    public static void show(String title, IntStream stream) {
        final int SIZE = 10;
        int[] firstElements = stream.limit(SIZE+1).toArray();
        System.out.println(title + ": ");
        for (int i = 0; i < firstElements.length; ++i) {
            if (i > 0) {
                System.out.print(", ");
            }
            if (i < SIZE) {
                System.out.print(firstElements[i]);
            } else {
                System.out.print("...");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        IntStream intStream = IntStream.generate(() -> (int)(Math.random() * 100));
        show("intStream", intStream);

        IntStream intStream1 = IntStream.range(1, 6);
        show("intStream1", intStream1);

        IntStream intStream2 = IntStream.rangeClosed(1, 6);
        show("intStream2", intStream2);

        Path path = Paths.get("./DesignPattern/src/practise/lios/demo/alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream intStream3 = words.mapToInt(String::length);
        show("intStream3", intStream3);

        String sentence = "\uD835\uDD46 is the set of octonions";
        System.out.println(sentence);

        IntStream codes = sentence.codePoints();
        System.out.println(codes.mapToObj(c -> String.format("%X", c)).collect(Collectors.joining()));

        Stream<Integer> integerStream = IntStream.range(1, 6).boxed();
        IntStream intStream4 = integerStream.mapToInt(Integer::intValue);
        show("intStream4", intStream4);
    }

}