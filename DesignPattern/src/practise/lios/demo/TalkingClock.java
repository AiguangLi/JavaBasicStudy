package practise.lios.demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

/**
 * @author liaiguang
 * @created 2020/6/6
 */
public class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        TimerCallBack callBack = new TimerCallBack();
        Timer timer = new Timer(interval, callBack);
        timer.start();
    }

    public void startLocalInner(int interval, boolean beep) {
        class LocalTimerCallBack implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("局部内部类当前时间：" + Instant.ofEpochMilli(e.getWhen()));
                if (beep) {
                    System.out.println("Beep!!!");
                } else {
                    System.out.println("Silence!!!");
                }
            }
        }
        Timer timer = new Timer(interval, new LocalTimerCallBack());
        timer.start();
    }

    public void startInnerAnonymous() {
        Timer timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("匿名内部类当前时间：" + Instant.ofEpochMilli(e.getWhen()));
                if (beep) {
                    System.out.println("Beep!!!");
                } else {
                    System.out.println("Silence!!!");
                }
            }
        });
        timer.start();
    }

    public class TimerCallBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("常规内部类，当前时间：" + Instant.ofEpochMilli(e.getWhen()));
            if (beep) {
                System.out.println("Beep!!!");
            } else {
                System.out.println("Silence!!!");
            }
        }
    }
}