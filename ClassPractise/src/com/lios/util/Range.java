package com.lios.util;

import java.util.ArrayList;

/**
 * @author liaiguang
 */
public class Range {
    final static int MAXIMUM_ARGUMENT_COUNT = 3;
    public static int[] produce(int number1, int...others) throws Exception {
        if (others.length > MAXIMUM_ARGUMENT_COUNT - 1) {
            throw new Exception("In Range.produce: Arguments exceed max argument count " + MAXIMUM_ARGUMENT_COUNT);
        }
        int step = 1;
        int start = 0, end;
        if (others.length == 0) {
            end = number1;
        } else if (others.length == 1) {
            start = number1;
            end = others[0];
        } else {
            start = number1;
            end = others[0];
            step = others[1];
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = start; i < end; i += step) {
            result.add(i);
        }

        ///通过流转换为int[]数组
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}
