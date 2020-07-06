package practise.lios.demo.streams;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author liaiguang
 * @date 2020/7/5
 */
public class CreatingStreams {
    public static<T> void show(String title, Stream<T> stream) {
        final int size = 10;
        List<T> firstElements = stream.limit(size + 1).collect(Collectors.toList());
        System.out.println(title + ": ");
        for (int i = 0; i < firstElements.size(); ++i) {
            if (i > 0) {
                System.out.print(", ");
            }
            if (i < size) {
                System.out.print(firstElements.get(i));
            } else {
                System.out.print("...");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        //获取当前路径
        System.out.println(System.getProperty("user.dir"));
        Path path = Paths.get("./DesignPattern/src/practise/lios/demo/alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words", words);
        //流用过后不可再用，因此需要新建一个流，skip为跳过前n个
        Stream<String> wordsSkip = Stream.of(contents.split("\\PL+"));
        show("wordsSkip", wordsSkip.skip(10));

        //流用过后不可再用，因此需要新建一个流，filter为过滤留下满足条件的元素
        Stream<String> wordsFilter = Stream.of(contents.split("\\PL+")).filter(s -> s.length() > 12);
        show("wordsFilter", wordsFilter);

        Stream<String> distinctStream = Stream.of(contents.split("\\PL+")).distinct();
        show("distinctStream", distinctStream);

        //takeWhile遇到第一个条件为假时停止，收集条件为假之前满足条件的元素
        Stream<String> takeWhileDigits = codePoints("1413988321中国s").takeWhile(s -> "0123456789".contains(s));
        show("takeWhileDigits", takeWhileDigits);

        //dropWhile产生一个由第一个使该条件为假的所有流
        Stream<String> dropWhileDigits = codePoints("12中国人口数1413988321中国s").dropWhile(s -> "0123456789".contains(s));
        show("dropWhileDigits", dropWhileDigits);

        Stream<String> songs = Stream.of("gently", "down", "the", "stream");
        show("song", songs);

        Stream<String> mapString = Stream.of("gently", "down", "the", "stream").map(String::toUpperCase);
        show("mapString", mapString);

        Stream<String> wordsStream = new Scanner(contents).tokens();
        Stream<String> flatMapStream = wordsStream.flatMap(CreatingStreams::codePoints);
        show("flatMapStream", flatMapStream);

        Stream<String> sortedString = Stream.of("gently", "down", "the", "stream").map(String::toUpperCase).sorted(Comparator.comparingInt(String::length));
        show("sortedString", sortedString);

        Stream<String> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> wordsByPattern = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsByPattern", wordsByPattern);

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            show("lines", lines);
        }

        String[] strings = new String[] {"gently", "down", "the", "stream"};
        Stream<String> arrayStreams = Arrays.stream(strings);
        show("arrayStreams", arrayStreams);

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(), false);
        show("rootDirectories", rootDirectories);

        Iterator<Path> iterator = Paths.get("~/Documents/").iterator();
        Stream<Path> pathStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
        show("pathStream", pathStream);
    }

    public static Stream<String> codePoints(String s) {
        ArrayList<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = s.offsetByCodePoints(i, 1);
            result.add(s.substring(i, j));
            i = j;
        }

        return result.stream();
    }

}