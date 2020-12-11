package net.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AioServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(5));

        AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(8899));

        channel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
                channel.accept(null, this);
            }

            @Override
            public void failed(Throwable exc, Void attachment) {

            }
        });

        group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

    }
}
