package practise.lios.demo.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @date 2020/7/6
 */
public class StreamCollectionResult {
    public static void main(String[] args) throws IOException {
        //获取当前路径
        System.out.println(System.getProperty("user.dir"));
        Path path = Paths.get("./DesignPattern/src/practise/lios/demo/alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        String[] words = contents.split("\\PL+");
        Optional<String> maxLengthWord = Stream.of(words).max(Comparator.comparing(String::length));
        System.out.println("Longest word: " + maxLengthWord.orElse(""));

        Optional<String> firstWordsStartWithQ = Stream.of(words).filter(s -> s.startsWith("Q")).findFirst();
        System.out.println("firstWordsStartWithQ: " + firstWordsStartWithQ.orElse(""));

        Optional<String> anyWordsStartWithE = Stream.of(words).filter(s -> s.startsWith("E")).findAny();
        System.out.println("anyWordsStartWithE: " + anyWordsStartWithE.orElse(""));

        boolean hasHello = Stream.of(words).anyMatch("Hello"::equals);
        System.out.println("hasHello: " + hasHello);
    }
}