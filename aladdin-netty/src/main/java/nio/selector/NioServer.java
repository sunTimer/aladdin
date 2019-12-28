package nio.selector;


import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NioServer {

    private static Map<String, SocketChannel> clientsCache = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            try {
                int size = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    SocketChannel client;
                    try {
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                            String key = "【" + UUID.randomUUID().toString() + "】";
                            clientsCache.put(key, client);
                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel) selectionKey.channel();
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int count = client.read(readBuffer);
                            if (count > 0) {
                                readBuffer.flip();
                                Charset charset = Charset.forName(CharsetUtil.UTF_8.name());
                                String receiveMsg = String.valueOf(charset.decode(readBuffer).array());
                                System.out.println(client + ":" + receiveMsg);

                                String clientKey = clientsCache.entrySet()
                                        .stream().filter(i -> i.getValue() == client)
                                        .findFirst().get().getKey();

                                clientsCache.forEach((key, sc) -> {
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                                    byteBuffer.put((clientKey + ":" + receiveMsg).getBytes());
                                    byteBuffer.flip();
                                    try {
                                        sc.write(byteBuffer);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    selectionKeys.remove(selectionKey);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
