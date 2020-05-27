package practise.lios.demo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liaiguang
 * @created 2020/5/26
 */
public class Sets<T> {
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        HashSet<T> result = new HashSet<>(a);
        result.addAll(b);

        return result;
    }

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        HashSet<T> result = new HashSet<>(a);
        result.retainAll(b);

        return result;
    }

    public static <T> Set<T> difference(Set<T> a, Set<T> b) {
        HashSet<T> result = new HashSet<>(a);
        result.removeAll(b);

        return result;
    }

    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
}