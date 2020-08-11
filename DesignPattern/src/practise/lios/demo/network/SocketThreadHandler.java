package practise.lios.demo.network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author liaiguang
 * @date 2020/8/11
 */
public class SocketThreadHandler implements Runnable {

    private Socket incoming;

    public SocketThreadHandler(Socket incoming) {
        this.incoming = incoming;
    }

    @Override
    public void run() {
        try (InputStream inputStream = incoming.getInputStream();
             OutputStream outputStream = incoming.getOutputStream();
             Scanner in = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            //autoFlush = true才会立即输出内容
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true
            );

            out.println("Hello! Enter BYE to exit.");

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Received: " + line);
                if ("BYE".equals(line.trim())) {
                    done = true;
                }
                Thread.sleep(50);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}