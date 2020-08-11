package practise.lios.demo.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    }
}