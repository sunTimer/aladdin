package nio.buffer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Niotest3 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\logs\\nio.log");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byteBuffer.put("hello nio".getBytes());
        byteBuffer.put("\nhello nio".getBytes());
        byteBuffer.flip();

        fileChannel.write(byteBuffer);
        fileChannel.close();
    }
}
