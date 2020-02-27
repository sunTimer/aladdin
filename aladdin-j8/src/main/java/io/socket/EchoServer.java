package io.socket;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 回音服务器。将客户端发送过来的消息回传给客户端。
 */
public class EchoServer {
    static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8899));
        while (true) {
            Socket connectedSocket = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = connectedSocket.getInputStream();
                        OutputStream outputStream = connectedSocket.getOutputStream();
                        while (true) {
                            int receiveBufferSize = connectedSocket.getReceiveBufferSize();
                            byte[] buffer = new byte[receiveBufferSize];
                            inputStream.read(buffer);
                            String msg = new String(buffer);
                            if (msg.equals("q")) {
                                System.out.println("close connect!bye");
                                connectedSocket.close();
                                return;
                            } else {
                                System.out.println(Thread.currentThread().getName() + "=" + msg + ",client=" + connectedSocket.getRemoteSocketAddress());
                                outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
