package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress socketAddress = new InetSocketAddress(8899);
        serverSocket.bind(socketAddress);
        while (true) {
            Socket socket = serverSocket.accept();

            Thread thread = new Thread(
                    () -> {
                        SocketChannel socketChannel = socket.getChannel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.put((byte) System.currentTimeMillis());
                        try {
                            socketChannel.write(byteBuffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            thread.start();
        }
    }
}
