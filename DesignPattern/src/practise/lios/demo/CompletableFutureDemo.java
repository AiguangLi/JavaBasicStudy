package practise.lios.demo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lios
 * @date 2020-07-02
 */
public class CompletableFutureDemo {
    private static final Pattern IMG_PATTER = Pattern.compile("[<]\\s*[iI][mM][gG]\\s*[^>]*[sS]" +
            "[rR][cC]\\s*[=]\\s*['\"]([^'\"]*)['\"][^>]*[>]");
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private URL urlToProcess;

    public CompletableFuture<String> readPage(URL url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String contents = new String(url.openStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println("Read page from " + url);

                return contents;
            } catch (IOException exception) {
                throw new UncheckedIOException(exception);
            }
        }, executorService);
    }

    public List<URL> getImageURLs(String webPage) {
        try {
            ArrayList<URL> result = new ArrayList<>();
            Matcher matcher = IMG_PATTER.matcher(webPage);
            while(matcher.find()) {
                URL url = new URL(urlToProcess, matcher.group(1));
                result.add(url);
            }
            System.out.println("Found URLs " + result);
            return result;
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    public CompletableFuture<List<BufferedImage>> getImages(List<URL> urls) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ArrayList<BufferedImage> result = new ArrayList<>();
                for (URL url: urls) {
                    result.add(ImageIO.read(url));
                    System.out.println("Loaded " + url);
                }

                return result;
            } catch (IOException exception) {
                throw new UncheckedIOException(exception);
            }
        });
    }

    public void saveImages(List<BufferedImage> images) {
        System.out.println("Saving " + images.size() + " images");
        try {
            for (int i = 0; i < images.size(); ++i) {
                String fileName = "/tmp/image" + (i+1) + ".png";
                ImageIO.write(images.get(i), "PNG", new File(fileName));
            }
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
        executorService.shutdown();
    }

    public void run(URL url) {
        urlToProcess = url;
        CompletableFuture.completedFuture(url)
                .thenComposeAsync(this::readPage, executorService)
                .thenApply(this::getImageURLs)
                .thenCompose(this::getImages)
                .thenAccept(this::saveImages);
    }

    public static void main(String[] args) throws MalformedURLException {
        new CompletableFutureDemo().run(new URL("https://horstmann.com/index.html"));
    }
}
