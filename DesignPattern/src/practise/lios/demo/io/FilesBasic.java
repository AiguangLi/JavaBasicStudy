package practise.lios.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

/**
 * @author liaiguang
 * @date 2020/7/26
 */
public class FilesBasic {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./DesignPattern/src/practise/lios/demo/alice.txt");
        //按行读取全部内容
        List<String> lines = Files.readAllLines(path);
        lines.stream().limit(10).forEach(System.out::println);

        //按字节读取全部内容
        byte[] bytes = Files.readAllBytes(path);
        String bytesToString = new String(bytes, 0 , 100);
        System.out.println("bytes:\n" + bytesToString);

        //通过InputStream读取内容
        InputStream inputStream = Files.newInputStream(path);
        Scanner inputScanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        final int maxLine = 10;
        int line = 0;
        System.out.println("Input Stream:");
        while(inputScanner.hasNextLine() && line < maxLine) {
            String contents = inputScanner.nextLine();
            line ++;
            System.out.println(contents);
        }

        //通过OutputStream写内容到文件
        Path outPath = Paths.get("output.txt");
        OutputStream outputStream = Files.newOutputStream(outPath, StandardOpenOption.CREATE);
        outputStream.write(bytes, 0, 100);
    }
}