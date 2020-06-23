package practise.lios.demo;

import java.util.Arrays;

/**
 * @author liaiguang
 */
public class BankTransfer {
    final static double INITIAL_BALANCE = 1000.0;
    final private double[] accounts = new double[10];
    final private Object lock = new Object();

    public BankTransfer() {
        Arrays.fill(accounts, INITIAL_BALANCE);
    }

    /**
     * 验证synchronized字句或synchronized块的线程同步
     * synchronized实际上是自动为方法加锁
     * @param from
     * @param to
     * @param amount
     * @throws IndexOutOfBoundsException
     * @throws IllegalArgumentException
     */
    public void transfer(int from, int to, double amount) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (from < 0 || from >= accounts.length) {
            throw new IndexOutOfBoundsException();
        }
        if (to < 0 || to >= accounts.length) {
            throw new IndexOutOfBoundsException();
        }

        if (accounts[from] > amount && from != to) {
            synchronized (lock) {
                accounts[from] -= amount;
                accounts[to] += amount;
            }
            System.out.printf("Transfer %.2f from %d to %d.\n" ,amount, from, to);
        }

        System.out.printf("Total Balance = %.2f.\n", getTotalBalance());
    }

    private double getTotalBalance() {
        double sum = 0.0;
        for (double d : accounts) {
            sum += d;
        }

        return sum;
    }

    public int accountSize() {
        return accounts.length;
    }
}
