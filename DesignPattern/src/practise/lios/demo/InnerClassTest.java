package practise.lios.demo;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

/**
 * @author liaiguang
 * @created 2020/6/6
 */
public class InnerClassTest {

    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();
        clock.startLocalInner(5000, false);
        clock.startInnerAnonymous();

        TalkingClock anotherClock = new TalkingClock(2000, false);
        //在外部调用内部类（公共内部类）需要指定外围类名称及创建内部类对象对应的外部类对象。
        TalkingClock.TimerCallBack callBack = anotherClock.new TimerCallBack();
        Timer timer = new Timer(2000, callBack);
        timer.start();

        double[] doubles = new Random(47).doubles(0, 1000).limit(10).toArray();
        Double[] doublesOfT = new Double[doubles.length];
        int index = 0;
        for (double d:doubles) {
            doublesOfT[index++] = d;
        };

        ArrayAlgorithm.Pair<Double> pair = ArrayAlgorithm.minMax(doublesOfT);
        System.out.println(Arrays.toString(doubles));
        System.out.println("Min: " + pair.getFirst() + "\tMax: " + pair.getSecond());

        String[] strings = new String[] {"a", "c", "bgd", "eda", "aeff"};
        ArrayAlgorithm.Pair<String> pairString = ArrayAlgorithm.minMax(strings);
        System.out.println(Arrays.toString(strings));
        System.out.println("Min: " + pairString.getFirst() + "\tMax: " + pairString.getSecond());

        JOptionPane.showMessageDialog(null, "退出程序？");
        System.exit(0);
    }
}