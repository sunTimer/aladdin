package net.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.bind(new InetSocketAddress(8333));
        socket.connect(new InetSocketAddress(8888));

        System.out.println();
    }

}
