package io.socket;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.setKeepAlive(true);
        socket.connect(new InetSocketAddress(8899));
        Scanner scanner = new Scanner(System.in);

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        while (true) {

            outputStream.write(scanner.next().getBytes(StandardCharsets.UTF_8));
            int receiveBufferSize = socket.getReceiveBufferSize();
            byte[] buffer = new byte[receiveBufferSize];
            inputStream.read(buffer);
            String msg = new String(buffer);
            if (msg.equals("q")) {
                System.out.println("close client! bye");
                socket.close();
                return;
            }
            System.out.println(msg);
        }

    }
}
