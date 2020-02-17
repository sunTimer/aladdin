package io;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NIO {

    public static void main(String[] args) throws IOException {
        String pathname = "/Users/shixu/IdeaProjects/aladdin/aladdin-j8/src/main/java/io/URLDemo.java";
        FileInputStream fileInputStream = new FileInputStream(pathname);

        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        while (channel.read(byteBuffer) != -1){
            byte b = byteBuffer.get();

            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8));

        }
    }
}
