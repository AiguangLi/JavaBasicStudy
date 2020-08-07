package practise.lios.demo.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * @author liaiguang
 * @date 2020/8/7
 */
public class MemoryMapFile {
    public static long checkSumWithInputStream(Path filename) throws IOException {
        try (InputStream inputStream = Files.newInputStream(filename)) {
            CRC32 crc32 = new CRC32();

            int c;
            while((c = inputStream.read()) != -1) {
                crc32.update(c);
            }

            return crc32.getValue();
        }
    }

    public static long checkSumWithBufferedInputStream(Path filename) throws IOException {
        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(filename))) {
            CRC32 crc32 = new CRC32();

            int c;
            while((c = inputStream.read()) != -1) {
                crc32.update(c);
            }

            return crc32.getValue();
        }
    }

    public static long checkSumWithRandomAccessFile(Path filename) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")) {
            long length = file.length();

            CRC32 crc32 = new CRC32();
            for (long i = 0; i < length; ++i) {
                file.seek(i);
                int c = file.readByte();
                crc32.update(c);
            }

            return crc32.getValue();
        }
    }

    public static long checkSumWithMappedFile(Path filename) throws IOException {
        try (FileChannel fileChannel = FileChannel.open(filename)) {
            CRC32 crc32 = new CRC32();
            int length = (int)fileChannel.size();
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);

            for (int i = 0; i < length; ++i) {
                int c = buffer.get(i);
                crc32.update(c);
            }

            return crc32.getValue();
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./DesignPattern/src/practise/lios/demo/alice.txt");

        long start = System.currentTimeMillis();
        long crcValue = checkSumWithInputStream(path);
        long end = System.currentTimeMillis();
        System.out.println("CRC value: " + Long.toHexString(crcValue));
        System.out.println("InputStream Running time: " + (end - start) + " milliseconds");

        start = System.currentTimeMillis();
        crcValue = checkSumWithBufferedInputStream(path);
        end = System.currentTimeMillis();
        System.out.println("CRC value: " + Long.toHexString(crcValue));
        System.out.println("BufferedInputStream Running time: " + (end - start) + " milliseconds");

        start = System.currentTimeMillis();
        crcValue = checkSumWithRandomAccessFile(path);
        end = System.currentTimeMillis();
        System.out.println("CRC value: " + Long.toHexString(crcValue));
        System.out.println("RandomAccessFile Running time: " + (end - start) + " milliseconds");

        start = System.currentTimeMillis();
        crcValue = checkSumWithMappedFile(path);
        end = System.currentTimeMillis();
        System.out.println("CRC value: " + Long.toHexString(crcValue));
        System.out.println("MappedFile Running time: " + (end - start) + " milliseconds");
    }
}