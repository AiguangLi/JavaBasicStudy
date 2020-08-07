package practise.lios.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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
        Path outputDirectory = Paths.get("output");
        //目录不存在的话创建目录
        if (! Files.exists(outputDirectory)) {
            Files.createDirectory(outputDirectory);
        }
        //将输出文件放入到新加的目录下
        Path outPath = outputDirectory.resolve("output.txt");
        boolean fileExists = Files.exists(outPath);
        OutputStream outputStream;
        if (fileExists) {
            outputStream = Files.newOutputStream(outPath, StandardOpenOption.APPEND);
        } else {
            outputStream = Files.newOutputStream(outPath, StandardOpenOption.CREATE);
        }
        outputStream.write(bytes, 0, 100);

        //文件移动、复制及删除
        Path parentPath = outputDirectory.toAbsolutePath().getParent();
        if (parentPath != null) {
            Path destinationFilePath = parentPath.resolve("output.txt");
            //如果文件存在则替换掉
            Path copyPath = Files.copy(outPath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

            Path movedFilePath = parentPath.resolve("output1.txt");
            Path movedPath = Files.move(outPath, movedFilePath, StandardCopyOption.REPLACE_EXISTING);

            showFileInfo(movedPath);

            Files.delete(outputDirectory);
        }

        Path anotherPath = Paths.get("./DesignPattern/src/practise/lios/demo");
        showSubPath(anotherPath);
    }

    public static void showFileInfo(Path path) throws IOException {
        boolean fileExists = Files.exists(path);
        System.out.println("File exists: " + fileExists);
        if (fileExists) {
            boolean isHidden = Files.isHidden(path);
            System.out.println("File is hidden: " + isHidden);

            boolean isReadable = Files.isReadable(path);
            System.out.println("File is readable: " + isReadable);

            boolean isWritable = Files.isWritable(path);
            System.out.println("File is writable: " + isWritable);

            boolean isExecutable = Files.isExecutable(path);
            System.out.println("File is executable: " + isExecutable);

            boolean isDirectory = Files.isDirectory(path);
            System.out.println("File is directory: " + isDirectory);

            boolean isSymbolicLink = Files.isSymbolicLink(path);
            System.out.println("File is symbolic link: " + isSymbolicLink);

            boolean isRegularFile = Files.isRegularFile(path);
            if (isRegularFile) {
                long fileSize = Files.size(path);
                System.out.printf("File size: %.1fkB \n",(float)fileSize / 1024);
                //BasicFileAttributes包含上述的全部属性封装
                BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
                System.out.println("File size: " + attributes.size() + "bytes");
                System.out.println("File created at: " + attributes.creationTime());
                System.out.println("File last modified at: " + attributes.lastModifiedTime());
                System.out.println("File last accessed at: " + attributes.lastAccessTime());
            }
        }
    }

    public static void showSubPath(Path path) throws IOException {
        try (Stream<Path> entries = Files.list(path)) {
            entries.forEach(p -> {
                if (Files.isDirectory(p)) {
                    System.out.println("Directory: " + p);
                } else {
                    System.out.println("File: " + p);
                }
            });
        }

        System.out.println("-------------Files.walk--------------");
        try (Stream<Path> entries = Files.walk(path)) {
            entries.forEach(p -> {
                if (Files.isDirectory(p)) {
                    System.out.println("Directory: " + p);
                } else {
                    System.out.println("File: " + p);
                }
            });
        }
    }
}