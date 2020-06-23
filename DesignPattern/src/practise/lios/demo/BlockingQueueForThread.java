package practise.lios.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 使用BlockingQueue完成生产者消费者线程同步
 * @author liaiguang
 */
public class BlockingQueueForThread {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREAD_COUNT = 100;
    private static final Path DUMMY = Paths.get("");
    private static BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /User):");
            String directory = in.nextLine();
            System.out.print("Enter keywords to search: ");
            String keyword = in.nextLine();
            Runnable enumerator = () -> {
                try {
                    enumerate(Paths.get(directory));
                    queue.put(DUMMY);
                } catch (IOException | InterruptedException exception) {
                    exception.printStackTrace();
                }
            };
            new Thread(enumerator).run();

            for (int i = 0; i < SEARCH_THREAD_COUNT; ++i) {
                Runnable searcher = () -> {
                    boolean done = false;
                    while (!done) {
                        Path file = null;
                        try {
                            file = queue.take();

                            if (file == DUMMY) {
                                queue.put(file);
                                done = true;
                            } else {
                                search(file, keyword);
                            }
                        } catch (InterruptedException | IOException exception) {
                            exception.printStackTrace();
                        }

                    }
                };
                new Thread(searcher).run();
            }
        };
    }

    private static void enumerate(Path path) throws IOException, InterruptedException {
        try (Stream<Path> children = Files.list(path)) {
            for (Path child : children.collect(Collectors.toList())) {
                if (Files.isDirectory(child)) {
                    enumerate(child);
                } else {
                    queue.put(child);
                }
            }
        }
    }

    private static void search(Path file, String keyword) throws IOException {
        try (Scanner in = new Scanner(file, StandardCharsets.UTF_8)) {
            int lineNumber= 0;
            while(in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n", file, lineNumber, line);
                }
            }
        }
    }

}
