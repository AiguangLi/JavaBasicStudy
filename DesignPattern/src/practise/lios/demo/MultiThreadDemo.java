package practise.lios.demo;

public class MultiThreadDemo {
    public static void main(String[] args) {
        BankTransfer bankTransfer = new BankTransfer();

        Runnable r = () -> {
            while (true) {
                int from = (int) (Math.random() * bankTransfer.accountSize());
                int to = (int) (Math.random() * bankTransfer.accountSize());
                double amount = Math.random() * BankTransfer.INITIAL_BALANCE;
                try {
                    bankTransfer.transfer(from, to, amount);
                } catch (IndexOutOfBoundsException | IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        };
        for (int i = 0;  i < 100; ++i) {
            Thread thread = new Thread(r);
            thread.start();

        }
    }

}
