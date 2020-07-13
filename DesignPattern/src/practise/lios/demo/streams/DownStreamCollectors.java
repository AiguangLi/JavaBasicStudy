package practise.lios.demo.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @date 2020/7/13
 */
public class DownStreamCollectors {
    public static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }


        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }
    }

    public static Stream<City> cities() {
        return Stream.of(
                new City("Haikou", "Hainan", 1340021),
                new City("Sanya", "Hainan", 840021),
                new City("Guangzhou", "Guangdong", 10239892),
                new City("Shenzhen", "Guangdong", 13429801),
                new City("Foshan", "Guangdong", 5460021));
    }

    public static void main(String[] args) {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocales = locales.collect(
                Collectors.groupingBy(Locale::getCountry, Collectors.toSet())
        );
        System.out.println("countryToLocales" + countryToLocales);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCount = locales.collect(
                Collectors.groupingBy(Locale::getCountry, Collectors.counting())
        );
        System.out.println("countryToLocaleCount" + countryToLocaleCount);

        Stream<City> cityStream = cities();
        Map<String, Integer> stateToCityPopulation = cityStream.collect(
                Collectors.groupingBy(City::getState,
                        Collectors.summingInt(City::getPopulation))
        );
        System.out.println("stateToCityPopulation" + stateToCityPopulation);

        cityStream = cities();
        Map<String, Optional<String>> stateToLongestCityName = cityStream.collect(
                Collectors.groupingBy(City::getState,
                        Collectors.mapping(City::getName, Collectors.maxBy(Comparator.comparing(String::length))
                        ))
        );

        System.out.println("stateToLongestCityName" + stateToLongestCityName);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(
                Collectors.groupingBy(
                        Locale::getDisplayCountry,
                        Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())
                )
        );
        System.out.println("countryToLanguages" + countryToLanguages);

        Map<String, IntSummaryStatistics> stateToPopulationSummary = cities().collect(
                Collectors.groupingBy(
                        City::getState,
                        Collectors.summarizingInt(City::getPopulation)
                )
        );
        System.out.println(stateToPopulationSummary.get("Hainan"));

        Map<String, String> stateToCityNames = cities().collect(
                Collectors.groupingBy(
                        City::getState,
                        Collectors.reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t)
                )
        );
        System.out.println(stateToCityNames);

        Map<String, String> stateToCityNamesByMapping = cities().collect(
                Collectors.groupingBy(
                        City::getState,
                        Collectors.mapping(City::getName, Collectors.joining(", "))
                )
        );
        System.out.println(stateToCityNamesByMapping);
    }
}