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
    public static class Pair {
        private final double first;
        private final double second;

        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

    public static Pair minMax(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (double v: values) {
            if (v > max) {
                max = v;
            }

            if (v < min) {
                min = v;
            }
        }

        return new Pair(min, max);
    }

}