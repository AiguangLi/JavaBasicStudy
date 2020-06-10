package practise.lios.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * 集成Formatter，覆盖format方法即可实现自定义日志格式化
 * @author liaiguang
 * @created 2020/6/10
 */
public class LogTextFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "]" + record.getMessage() + "\n";
    }
}