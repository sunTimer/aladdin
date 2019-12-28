package nio.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\logs\\controller.log");

        try (FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                byte b = byteBuffer.get();
                System.out.println((char) b);
            }
        }
    }
}
