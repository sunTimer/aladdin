package io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ServerSocketChannelDemo {


    public static void main(String[] args) throws IOException {
        serverSocketChannel();
    }

    public static void serverSocketChannel() throws IOException {

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.socket().bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int num = selector.select();
            System.out.println("准备好的事件个数：" + num);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey key : selectionKeys) {
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) ==
                        SelectionKey.OP_ACCEPT) {
                    System.out.println("接收到accept事件。");
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel1 = channel.accept();
                    socketChannel1.configureBlocking(false);
                    socketChannel1.register(selector, SelectionKey.OP_READ);
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    System.out.println("接收到read事件。");
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (channel.read(buffer) > 0) {
                        System.out.println(new String(buffer.array()));
                    }
                }
            }

        }
    }

}

class Client2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8899));

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello world".getBytes());
    }
}
