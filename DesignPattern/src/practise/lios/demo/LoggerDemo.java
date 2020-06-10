package practise.lios.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author liaiguang
 * @created 2020/6/8
 */
public class LoggerDemo {
    /**
     * 使用静态变量防止被垃圾回收
     */
    private static final Logger myLogger = Logger.getLogger("practise.lios.demo.LoggerDemo");
    public static void main(String[] args) throws IOException {
        //修改默认的Logger配置文件，支持打印Info以下级别日志。
//        String loggingProperties = System.getProperty("user.dir")+"/DesignPattern/src/logging.properties";
//        System.setProperty("java.util.logging.config.file", loggingProperties);
//        LogManager.getLogManager().readConfiguration();
        //不使用默认的日志处理器，使用文件处理日志
        myLogger.setUseParentHandlers(false);
        //文件默认在用户根目录下
        //FileHandler fileHandler = new FileHandler();
        //fileHandler.setFormatter();
        WindowHandler handler = new WindowHandler();
        handler.setFormatter(new LogTextFormatter());
        myLogger.addHandler(handler);
        int loopNumber = 10;
        myLogger.setLevel(Level.INFO);
        for (int i = 0; i < loopNumber; ++i) {
            if (i == 5) {
                //被LogFilter过滤掉了
                myLogger.info("i=5");
            } else {
                myLogger.info("Log i=" + i);
            }
        }

        int[] numbers = new int[]{1, 2, 4, 6, 8, 10};

        int max = getMax(numbers);
        myLogger.fine("max=" + max);
    }


    private static int getMax(int[] array) {
        assert array != null;
        myLogger.entering("LoggerDemo", "getMax", Arrays.toString(array));
        int max = Integer.MIN_VALUE;
        for (int i: array) {
            if (max < i) {
                max = i;
            }
        }

        myLogger.exiting("LoggerDemo", "getMax");

        return max;
    }
}