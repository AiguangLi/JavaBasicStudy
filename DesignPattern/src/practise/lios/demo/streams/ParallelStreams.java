package practise.lios.demo.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liaiguang
 * @date 2020/7/15
 */
public class ParallelStreams {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./DesignPattern/src/practise/lios/demo/alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> wordsList = List.of(contents.split("\\PL+"));

        final int SHORT_LENGTH = 10;
        int[] shortWords = new int[SHORT_LENGTH];
        wordsList.parallelStream().forEach(s -> {
            if (s.length() < SHORT_LENGTH) {
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));

        //并行计算中如果在forEach进行运算，每次结果都可能不同
        Arrays.fill(shortWords, 0);
        wordsList.parallelStream().forEach(s -> {
            if (s.length() < SHORT_LENGTH) {
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));

        Map<Integer, Long> shortWordsCount = wordsList.parallelStream().
                filter(s -> s.length() < 10).
                collect(
                        Collectors.groupingBy(String::length, Collectors.counting())
                );


        System.out.println(shortWordsCount);

        Map<Integer, List<String>> result = wordsList.parallelStream().
                collect(Collectors.groupingByConcurrent(String::length)
                );
        System.out.println(result.get(14));

        result = wordsList.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        Map<Integer, Long> wordsCount = wordsList.parallelStream().
                collect(
                        Collectors.groupingBy(String::length, Collectors.counting())
                );
        System.out.println(wordsCount);
    }
}