package io;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

public class ServerSocketDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress socketAddress = new InetSocketAddress(8899);
        serverSocket.bind(socketAddress);

        while (true) {

            Socket accept = serverSocket.accept();

            InputStream inputStream = accept.getInputStream();

            byte[] buffer = new byte[1024];
            inputStream.read(buffer);

            System.out.println("from " + accept.getRemoteSocketAddress()
                    + ". received msg :" + new String(buffer));

        }
    }

}

class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8899));

        OutputStream outputStream = socket.getOutputStream();

        for (int i = 0; i < 10; i++) {
            outputStream.write("hello world".getBytes(Charset.defaultCharset()));
        }
    }
}