package practise.lios.demo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author liaiguang
 * @date 2020/7/23
 */
public class ZipFileTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("test.zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        String[] filenames = new String[]{"zip1.txt", "zip2.txt", "zip3.txt"};
        for (String filename : filenames) {
            ZipEntry ze = new ZipEntry(filename);
            zipOutputStream.putNextEntry(ze);
            writeData("zip1.txt", zipOutputStream);
            zipOutputStream.closeEntry();
            //zipOutputStream.write(DataIO.);
        }
        zipOutputStream.close();

        FileInputStream inputStream = new FileInputStream("test.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry entry;

        while((entry =  zipInputStream.getNextEntry()) != null) {
            String s = readData(zipInputStream);
            System.out.println(s);
            zipInputStream.closeEntry();
        }
        zipInputStream.close();

    }

    public static void writeData(String s, ZipOutputStream zipOutputStream) throws IOException {
        byte[] bytes = s.getBytes();
        zipOutputStream.write(bytes);
    }

    public static String readData(ZipInputStream zipInputStream) throws IOException {
        byte[] bytes = zipInputStream.readAllBytes();
        return new String(bytes);
    }
}