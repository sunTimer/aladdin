package nio.selector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress(8899));


        while (true) {
            selector.select();
            Set<SelectionKey> keySet = selector.selectedKeys();

            for (SelectionKey selectionKey : keySet) {
                SocketChannel client;
                if (selectionKey.isConnectable()) {
                    client = (SocketChannel) selectionKey.channel();
                    if (client.isConnectionPending()) {
                        client.finishConnect();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.put((LocalDate.now().toString() + "连接成功。").getBytes());
                        byteBuffer.flip();
                        client.write(byteBuffer);

                        ExecutorService executorService = Executors.newSingleThreadExecutor();
                        executorService.submit(() -> {
                            byteBuffer.clear();
                            InputStreamReader reader = new InputStreamReader(System.in);
                            BufferedReader bf = new BufferedReader(reader);

                            String sendMsg;
                            try {
                                sendMsg = bf.readLine();
                                byteBuffer.put(sendMsg.getBytes());
                                byteBuffer.flip();
                                client.write(byteBuffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    client.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    client = (SocketChannel) selectionKey.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int count = client.read(byteBuffer);

                    if (count > 0) {
                        String receiveMsg = new String(byteBuffer.array(), 0, count);
                        System.out.println(receiveMsg);
                    }
                }
                keySet.remove(selectionKey);
            }

        }

    }
}
