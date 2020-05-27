package practise.lios.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liaiguang
 * @created 2020/5/26
 */
public class SetsTest {
    public static void main(String[] args) {
        String[] collectionA = new String[]{"a", "b", "c", "d", "e", "f"};
        String[] collectionB = new String[]{"g", "h", "a", "d", "e", "f"};

        Set<String> setA = new HashSet<>(Arrays.asList(collectionA));
        Set<String> setB = new HashSet<>(Arrays.asList(collectionB));

        System.out.println(Sets.difference(setA, setB));
        System.out.println(Sets.union(setA, setB));
        System.out.println(Sets.intersection(setA, setB));
        System.out.println(Sets.complement(setA, setB));
    }
}