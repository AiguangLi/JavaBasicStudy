package practise.lios.demo;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author liaiguang
 * @created 2020/5/24
 */
public class LotteryGen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("输入彩票最大号码：");
        int maxLotteryNumber = scanner.nextInt();
        int[] lotteryNumberPool = new int[maxLotteryNumber];
        for (int i = 1; i <= maxLotteryNumber; ++i) {
            lotteryNumberPool[i-1] = i;
        }

        System.out.println("输入中奖号码个数：");
        int awardNumber = scanner.nextInt();

        if (awardNumber > maxLotteryNumber) {
            System.out.println("中间号码个数不可超过" + maxLotteryNumber);
        }

        int[] awardResult = new int[awardNumber];

        int maxPoolIndex = lotteryNumberPool.length;
        for (int i = 0; i < awardResult.length; ++i) {
            int index = (int) (Math.random() * maxPoolIndex);
            awardResult[i] = lotteryNumberPool[index];
            lotteryNumberPool[index] = lotteryNumberPool[maxPoolIndex-1];
            maxPoolIndex = maxPoolIndex - 1;
        }

        Arrays.sort(awardResult);

        BigInteger lotterySum = BigInteger.valueOf(1);
        //中奖概率C(n,k)，即从1到n中选择k个数的组合，总共有n * (n - 1) * (n - 2) * ... * (n - k + 1) / (1 * 2 * 3 * ... * k)
        for (int i = 1; i <= awardResult.length; ++i) {
            lotterySum = lotterySum.multiply(BigInteger.valueOf(maxLotteryNumber - i + 1)).divide(BigInteger.valueOf(i));
        }

        System.out.println("从1到" + maxLotteryNumber + "中抽取" + awardNumber + "个数，若抽中数字与下面数字一致，将中大奖：");
        System.out.println(Arrays.toString(awardResult));

        System.out.println("中奖概率为：1/" + lotterySum);

        LocalDate.now();
    }
}