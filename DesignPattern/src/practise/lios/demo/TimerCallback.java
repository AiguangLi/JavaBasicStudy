package practise.lios.demo;

import javax.swing.*;
import java.time.Instant;

/**
 * @author liaiguang
 * @created 2020/6/3
 */
public class TimerCallback  {

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("时间响应时间="+ Instant.ofEpochMilli(e.getWhen()));
//        Toolkit.getDefaultToolkit().beep();
//    }

    public static void main(String[] args) {
        //ActionListener listener = new TimerCallback();

        // 使用lambda表达式更简单
        Timer timer = new Timer(1000,
                event -> System.out.println("时间响应时间="+ Instant.ofEpochMilli(event.getWhen())));
        timer.start();

        JOptionPane.showMessageDialog(null, "退出程序？");
        System.exit(0);
    }
}