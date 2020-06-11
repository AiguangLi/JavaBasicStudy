package practise.lios.demo;

/**
 * @author liaiguang
 * @created 2020/6/7
 */
public class ArrayAlgorithm {
    /**
     * 静态内部类无法捕获外围类的成员
     * 但可以封装对象返回多个值，且避免类名重名
     */
    public static class Pair<T> {
        private final T first;
        private final T second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }
    }

//    public static Pair<String> minMax(String[] values) {
//        if (values == null || values.length == 0) {
//            return null;
//        }
//        String min = values[0];
//        String max = values[0];
//        //double min = Double.POSITIVE_INFINITY;
//        //double max = Double.NEGATIVE_INFINITY;
//
//        for (String v: values) {
//            if (v.compareTo(min) > 0) {
//                min = v;
//            }
//
//            if (v.compareTo(max) > 0) {
//                max = v;
//            }
//        }
//
//        return new Pair<String>(min, max);
//    }

    public static <T extends Comparable<T>> Pair<T> minMax(T[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        T min = values[0];
        T max = values[0];
        //double min = Double.POSITIVE_INFINITY;
        //double max = Double.NEGATIVE_INFINITY;

        for (T v: values) {
            if (v.compareTo(min) < 0) {
                min = v;
            }

            if (v.compareTo(max) > 0) {
                max = v;
            }
        }

        return new Pair<T>(min, max);
    }

}