package practise.lios.demo.streams;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @date 2020/7/16
 */
public class StreamSummary {
    public static class User {
        private String name;
        private int id;
        //1=male, 0 = female, 2= unknown
        private int sex = 2;

        public User(int id, String name, int sex) {
            this.id = id;
            this.name = name;
            if (sex == 0 || sex == 1) {
                this.sex = sex;
            }
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getSex() {
            return sex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id &&
                    sex == user.sex &&
                    name.equals(user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, id, sex);
        }

        @Override
        public String toString() {
            return "User{id = " + id + ", name = " + name + ", sex = " + sex + "}";
        }
    }

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

        //获取单词的数量
        long wordsCount = Pattern.compile("\\PL+").splitAsStream(sentences).filter(s -> s.length() > 2).count();
        System.out.println("Number of words length greater than 2: " + wordsCount);

        //获取最长的单词
        Optional<String> longestWord = Pattern.compile("\\PL+").splitAsStream(sentences).max(Comparator.comparing(String::length));
        System.out.println("longestWord: " + longestWord.orElse("null"));

        //获取最短的单词
        Optional<String> shortestWord = Pattern.compile("\\PL+").splitAsStream(sentences).min(Comparator.comparing(String::length));
        System.out.println("shortestWord: " + shortestWord.orElse("null"));

        //获取第一个以h开头的单词
        Optional<String> firstWordsStartWithH = Pattern.compile("\\PL+").splitAsStream(sentences)
                .filter(s -> s.toLowerCase().startsWith("h")).findFirst();
        System.out.println("firstWordsStartWithH: " + firstWordsStartWithH.orElse("Not Found"));

        //获取任何一个以h开头的单词
        Optional<String> anyWordsStartWithH = Pattern.compile("\\PL+").splitAsStream(sentences)
                .filter(s -> s.toLowerCase().startsWith("h")).findAny();
        System.out.println("anyWordsStartWithH: " + anyWordsStartWithH.orElse("Not Found"));

        //查找其中是否有was这个单词
        boolean hasWas = Pattern.compile("\\PL+").splitAsStream(sentences).anyMatch("was"::equals);
        System.out.println("hasWas: " + hasWas);

        //查找其中全部是was这个单词
        boolean allWas = Pattern.compile("\\PL+").splitAsStream(sentences).allMatch("was"::equals);
        System.out.println("allWas: " + allWas);

        //使用forEach方法打印全部单词的大写
        Pattern.compile("\\PL+").splitAsStream(sentences).map(String::toUpperCase).forEach(System.out::print);

        //将流转为Collection
        List<String> wordsList = Pattern.compile("\\PL+").splitAsStream(sentences).collect(Collectors.toList());
        System.out.println("wordsList: " + wordsList);

        //将流转为数组
        String[] wordsArray = Pattern.compile("\\PL+").splitAsStream(sentences).toArray(String[]::new);
        System.out.println("wordsArray: " + Arrays.toString(wordsArray));

        //将流拼接为字符串
        String wordsToSentences = Pattern.compile("\\PL+").splitAsStream(sentences).collect(Collectors.joining(", "));
        System.out.println("wordsToSentences: " + wordsToSentences);

        IntSummaryStatistics lengthSummary = Pattern.compile("\\PL+").splitAsStream(sentences).
                collect(Collectors.summarizingInt(String::length));
        System.out.println("Longest word length: " + lengthSummary.getMax());
        System.out.println("Shortest word length: " + lengthSummary.getMin());
        System.out.println("Average word length: " + lengthSummary.getAverage());

        //映射为id -> User格式
        Map<Integer, User> userMap = userStream().collect(Collectors.toMap(User::getId, Function.identity()));
        for (Integer key:userMap.keySet()) {
            System.out.println("id " + key + ": " + userMap.get(key));
        }

        //映射为id -> User.name格式
        Map<Integer, String> idToNameMap = userStream().collect(Collectors.toMap(User::getId, User::getName));
        System.out.println(idToNameMap);

        //按性别分组
        Map<Integer, Set<User>> sexToUserMap = userStream().collect(Collectors.groupingBy(User::getSex, Collectors.toSet()));
        for (Integer key:sexToUserMap.keySet()) {
            Set<User> usersOfSex = sexToUserMap.get(key);
            System.out.println("Sex " + key + ": " + usersOfSex);
        }
    }

    public static Stream<User> userStream() {
        return Stream.of(
                new User(1, "Box", 1),
                new User(2, "Succi", 0),
                new User(3, "Lily", 0),
                new User(4, "Sara", 0),
                new User(5, "Mark", 1)
        );
    }

    public static <T> void show(String title, Stream<T> stream) {
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