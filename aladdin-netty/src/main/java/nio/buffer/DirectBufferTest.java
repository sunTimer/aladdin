package nio.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DirectBufferTest {

    public static void main(String[] args) throws IOException {
        FileInputStream fi = new FileInputStream("input.txt");
        FileOutputStream fo = new FileOutputStream("output.txt");

        FileChannel inputChannel = fi.getChannel();
        FileChannel outputChannel = fo.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(16);

        byteBuffer.putInt(1);
        byteBuffer.putInt(4234);
        while (true) {
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            System.out.println("read:" + read);
            if (read == -1) {
                break;
            }

            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }
        inputChannel.close();
        outputChannel.close();

    }
}
