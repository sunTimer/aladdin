package io.socket.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

public class EchoServerNio {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        ServerSocketChannel channel = serverSocket.getChannel();
    }
}
