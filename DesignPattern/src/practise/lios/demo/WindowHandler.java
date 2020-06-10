package practise.lios.demo;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * 继承StreamHandler即可实现自定义的日志输出处理器
 * @author liaiguang
 * @created 2020/6/10
 */
public class WindowHandler extends StreamHandler {
    private final JFrame frame;
    public WindowHandler() {
        frame = new JFrame();
        JTextArea output = new JTextArea();
        output.setEditable(false);
        frame.setSize(600, 600);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }

            @Override
            public void write(byte[] b, int off, int len) {
                output.append(new String(b, off, len));
            }
        });
    }

    @Override
    public void publish(LogRecord record) {
        if (! frame.isVisible()) {
            return;
        }
        super.publish(record);
        flush();
    }
}