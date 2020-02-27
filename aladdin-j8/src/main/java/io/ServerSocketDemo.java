package io;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;

public class ServerSocketDemo {



    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress socketAddress = new InetSocketAddress(8899);
        serverSocket.bind(socketAddress);
        SocketAddress localSocketAddress = serverSocket.getLocalSocketAddress();
        System.out.println(localSocketAddress);

        while (true) {
            Socket connectedSocket = serverSocket.accept();
            System.out.println("server socket:" + connectedSocket.hashCode());
            System.out.println("server port:" + connectedSocket.getLocalPort());
            InputStream inputStream = connectedSocket.getInputStream();
            byte[] buffer = new byte[1024];
            inputStream.read(buffer);
            System.out.println("from " + connectedSocket.getRemoteSocketAddress()
                    + ". received msg :" + new String(buffer));
            connectedSocket.close();
        }
    }

}

class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8899));

        OutputStream outputStream = socket.getOutputStream();

        System.out.println("local port:" + socket.getLocalPort());
        for (int i = 0; i < 10; i++) {
            outputStream.write("hello world".getBytes(Charset.defaultCharset()));
        }
    }
}