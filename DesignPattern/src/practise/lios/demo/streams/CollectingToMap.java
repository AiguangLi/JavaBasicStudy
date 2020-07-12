package practise.lios.demo.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @date 2020/7/12
 */
public class CollectingToMap {
    public static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static Stream<Person> people() {
        return Stream.of(
                new Person(1001, "Mark"),
                new Person(1002, "Max"),
                new Person(1003, "Bill")
                //,
                //new Person(1003, "Lily")
        );
    }

    public static void main(String[] args) {
        Map<Integer, String> idToName = people().collect(Collectors.toMap(
                Person::getId,
                Person::getName,
                (existingValue, newValue) -> existingValue
        ));
        System.out.println("idToName: " + idToName);

        Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(
                Person::getId,
                Function.identity(),
                (existingValue, newValue) -> existingValue
                ) );
        System.out.println("idToPerson: " + idToPerson);

//        idToPerson = people().collect(Collectors.toMap(
//                Person::getId,
//                Function.identity(),
//                (existingValue, newValue) -> existingValue)
//        );
//        System.out.println("idToPerson: " + idToPerson);

        try {
            idToPerson = people().collect(
                    Collectors.toMap(
                            Person::getId,
                            Function.identity(),
                            (existingValue, newValue) -> {
                                throw new IllegalStateException();
                            },
                            TreeMap::new
                    )
            );
            System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);
        } catch (IllegalStateException exception) {
            exception.printStackTrace();
        }

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> languageNames = locales.collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l -> Set.of(l.getDisplayLanguage()),
                        (a, b) -> {
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }));
        System.out.println("languageNames: " + languageNames);
    }
}