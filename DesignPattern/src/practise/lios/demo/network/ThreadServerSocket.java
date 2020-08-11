package practise.lios.demo.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liaiguang
 * @date 2020/8/11
 */
public class ThreadServerSocket {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            int i = 1;
            while (true) {
                Socket in = serverSocket.accept();
                System.out.println("Socket Client：" + i);
                Thread socketThread = new Thread(new SocketThreadHandler(in));
                socketThread.start();
                i++;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}