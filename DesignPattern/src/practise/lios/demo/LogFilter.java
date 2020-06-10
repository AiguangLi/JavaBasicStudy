package practise.lios.demo;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * 实现Filter接口的isLoggable接口，即可实现自定义的日志过滤器
 * @author liaiguang
 * @created 2020/6/10
 */
public class LogFilter implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        return record.getMessage().startsWith("Log");
        //return false;
    }
}