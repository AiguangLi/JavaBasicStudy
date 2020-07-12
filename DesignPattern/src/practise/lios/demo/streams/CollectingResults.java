package practise.lios.demo.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @date 2020/7/11
 */
public class CollectingResults {
    public static Stream<String> noVowels() throws IOException {
        Path path = Paths.get("./DesignPattern/src/practise/lios/demo/alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> wordsList = List.of(contents.split("\\PL+"));

        Stream<String> words = wordsList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]]", ""));
    }

    public static<T> void show(String label, Set<T> set) {
        System.out.println(label + ": " + set.getClass().getName());
        System.out.println("[" +
                set.stream().limit(10).map(Objects::toString).collect(Collectors.joining(", ")) +
                "]"
        );
    }

    public static void main(String[] args) throws IOException {
        Iterator<Integer> iterator = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object Array: " + Arrays.toString(numbers));

        try {
            Integer number = (Integer) numbers[0];
            System.out.println("number: " + number);
            System.out.println("The following statement throws an exception.");
            Integer[] number2 = (Integer[]) numbers;
        } catch (ClassCastException exception) {
            System.out.println(exception);
        }

        Integer[] numbers2 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer Array: " + Arrays.toString(numbers2));

        Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
        show("noVowelSet: ",  noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet: ",  noVowelTreeSet);

        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining: " + result);

        String joinWithComma = noVowels().limit(10).collect(Collectors.joining(", "));
        System.out.println("join with comma: " + joinWithComma);

        IntSummaryStatistics summary =  noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWordsLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        System.out.println("Average Words Length: " + averageWordsLength);

        System.out.println("Max Words Length: " + maxWordLength);

        System.out.println("For Each: ");
        noVowels().limit(10).forEach(System.out::println);
    }
}