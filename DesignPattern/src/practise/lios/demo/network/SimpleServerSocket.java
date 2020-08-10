package practise.lios.demo.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author liaiguang
 * @date 2020/8/10
 */
public class SimpleServerSocket {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8188)) {
            try (Socket incoming= serverSocket.accept()) {
                InputStream inputStream = incoming.getInputStream();
                OutputStream outputStream = incoming.getOutputStream();

                try (Scanner in = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                    //autoFlush = true才会立即输出内容
                    PrintWriter out = new PrintWriter(
                            new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true
                    );

                    out.println("Hello! Enter BYE to exit.");

                    boolean done = false;
                    while(!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        out.println("Received: " + line);
                        if ("BYE".equals(line.trim())) {
                            done = true;
                        }
                    }
                }
            }
        }
    }
}