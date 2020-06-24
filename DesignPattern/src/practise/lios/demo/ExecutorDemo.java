package practise.lios.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liaiguang
 * @date 2020/6/24
 */
public class ExecutorDemo {
    public static long occurrences(String word, Path path) {
        try (Scanner in = new Scanner(path)) {
            int count = 0;
            while (in.hasNext()) {
                if (in.next().equals(word)) {
                    count++;
                }
            }
            return count;
        } catch (IOException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static Set<Path> deascendants(Path rootDir) throws IOException {
        try (Stream<Path>entries = Files.walk(rootDir)) {
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }

    public static Callable<Path> searchForTask(String word, Path path) {
        return () -> {
            try (Scanner in = new Scanner(path)) {
                while (in.hasNext()) {
                    if (in.next().equals(word)) {
                        return path;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Search in " + path + " canceled. ");
                        return null;
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            throw new NoSuchElementException();
        };
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        try(Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /User):");
            String directory = in.nextLine();
            System.out.print("Enter keywords to search: ");
            String keyword = in.nextLine();

            Set<Path> files = deascendants(Paths.get(directory));
            ArrayList<Callable<Long>> tasks = new ArrayList<>();
            for (Path path: files) {
                Callable<Long> task = () -> occurrences(keyword, path);
                tasks.add(task);
            }
            ExecutorService executor = Executors.newSingleThreadExecutor();

            Instant startTime = Instant.now();
            List<Future<Long>> futures = executor.invokeAll(tasks);
            long total = 0;
            for (Future<Long> result: futures) {
                total += result.get();
            }
            Instant endTime = Instant.now();
            System.out.println("Occurrences of " + keyword + ": " + total);
            System.out.println("Time elapsed: " + Duration.between(startTime, endTime).toMillis() + " ms");

            ArrayList<Callable<Path>> tasksAny = new ArrayList<>();
            for (Path file: files) {
                tasksAny.add(searchForTask(keyword, file));
            }
            Path found = executor.invokeAny(tasksAny);
            System.out.println(keyword + " occurs in: " + found);

            if (executor instanceof ThreadPoolExecutor) {
                System.out.println("Largest pool size: " + ((ThreadPoolExecutor)executor).getLargestPoolSize());
            }
            executor.shutdown();
        }
    }
}