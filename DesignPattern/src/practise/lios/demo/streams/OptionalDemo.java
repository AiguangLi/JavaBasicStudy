package practise.lios.demo.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author liaiguang
 * @date 2020/7/9
 */
public class OptionalDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./DesignPattern/src/practise/lios/demo/alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+"));

        Optional<String> optionalValue = words.stream().filter(s -> s.contains("fred")).findFirst();
        System.out.println(optionalValue.orElse("fred Not Found."));

        Optional<String> empty = Optional.empty();
        System.out.println(empty.orElse("N/A"));

        String result = empty.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println(empty.orElse(result));

        try {
            result = empty.orElseThrow(IllegalStateException::new);
            System.out.println(result);
        } catch (IllegalStateException e) {
            e.printStackTrace();;
        }

        optionalValue = words.stream().filter(s -> s.contains("red")).findFirst();
        optionalValue.ifPresent(System.out::println);

        HashSet<String> stringHashSet = new HashSet<>();
        optionalValue.ifPresent(stringHashSet::add);
        Optional<Boolean> added = optionalValue.map(stringHashSet::add);
        System.out.println("Optional added: " + added);

        System.out.println(inverse(4.0).flatMap(OptionalDemo::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalDemo::squareRoot));
        System.out.println(inverse(0.0).flatMap(OptionalDemo::squareRoot));
        Optional<Double> result2 = Optional.of(4.0).flatMap(OptionalDemo::inverse).flatMap(OptionalDemo::squareRoot);
        System.out.println(result2);
    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

}