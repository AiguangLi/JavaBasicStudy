package practise.lios.demo.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author liaiguang
 * @date 2020/8/11
 */
public class WebFetch {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://app.huiguanjia.cn/");
        try(InputStream inputStream = url.openStream()) {

            Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }

        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(60000);
        connection.connect();

        //获取header信息
        Map<String, List<String>> headers = connection.getHeaderFields();
        for(Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            for (String value: entry.getValue()) {
                System.out.println(key + ": " + value);
            }
        }

        //获取响应信息
        System.out.println("====================");
        System.out.println("ContentType: " + connection.getContentType());
        System.out.println("ContentLength: " + connection.getContentLength());
        System.out.println("ContentEncoding: " + connection.getContentEncoding());
        System.out.println("Date: " + connection.getDate());
        System.out.println("Expiration: " + connection.getExpiration());
        System.out.println("LastModified: " + connection.getLastModified());
        System.out.println("====================");

        //通过encoding获取内容
        String encoding = connection.getContentEncoding();
        if (encoding == null) {
            encoding = StandardCharsets.UTF_8.toString();
        }

        try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8)) {
            int lines = 0;
            final int MAX_LINE = 10;
            while (scanner.hasNextLine() && lines < MAX_LINE) {
                System.out.println(scanner.nextLine());
                lines++;
            }
            if (lines >= MAX_LINE) {
                System.out.println("......");
            }
        }
    }
}