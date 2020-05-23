package practise.lios.util;

import java.util.ArrayList;

/**
 * @author liaiguang
 */
public class Range {
    final static int MAXIMUM_ARGUMENT_COUNT = 3;

    /**
     * produce a int array in range
     * if others empty, produce 0,1,2,...,number1-1
     * if others have one arguments, produce number1, number1+1, number1+2, ..., others[0]-1
     * if others have two arguments, produce number1, number1+others[1], ..., , number1+n*others[1],
     * util not great equal others[0]
     * @param number1
     * @param others
     * @return
     * @throws Exception
     */
    public static int[] range(int number1, int...others) throws Exception {
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


        if (step == 0) {
            return new int[0];
        }
        int resultLen  = (end - start) / step;
        //存在余数，长度需要加1
        if ((end - start) % step > 0) {
            resultLen += 1;
        }
        int[] result = new int[resultLen];
        int index = 0;
        for (int i = start; i < end; i += step) {
            result[index++] = i;
        }

        return result;
//        ArrayList<Integer> result = new ArrayList<>();
//        for (int i = start; i < end; i += step) {
//            result.add(i);
//        }
//
//        ///通过流转换为int[]数组
//        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}
