package net.http;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentSocketServer {

    static ExecutorService executors = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws IOException {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    server();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void server() throws IOException {
        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress(8089));

        while (true) {
            Socket accept = socket.accept();
            executors.execute(() -> {
                try {
                    System.out.println("接收到客户端请求：" + accept.getPort());
                    InputStream inputStream = accept.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    OutputStream outputStream = accept.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

                    while ((line = reader.readLine()) != null) {
                        if (line.equals("exit")) {
                            writer.close();
                            inputStream.close();
                            accept.close();
                            break;
                        }
                        System.out.println(line);
                        writer.write(line + "\n");
                        writer.flush();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
