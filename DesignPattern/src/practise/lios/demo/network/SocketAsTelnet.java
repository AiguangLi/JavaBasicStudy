package practise.lios.demo.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author liaiguang
 * @date 2020/8/10
 */
public class SocketAsTelnet {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("time-a.nist.gov", 13);
             Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8)) {
            while(in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }

        Socket s = new Socket();
        s.setSoTimeout(1000);
        try {
            s.connect(new InetSocketAddress("time-a.nist.gov", 13), 1000);
            try (Scanner in = new Scanner(s.getInputStream(), StandardCharsets.UTF_8)) {
                while(in.hasNextLine()) {
                    String line = in.nextLine();
                    System.out.println(line);
                }
            }
        }  catch (SocketTimeoutException exception) {
            System.out.println(exception.getMessage());
        }

        Scanner input = new Scanner(System.in);
        String host = "";
        while(! "q".equals(host.toLowerCase())) {
            host = input.nextLine();
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for(InetAddress a : addresses) {
                System.out.println(a);
            }
        }

    }
}