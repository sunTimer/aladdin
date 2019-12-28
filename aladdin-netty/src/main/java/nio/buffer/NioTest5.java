package nio.buffer;

import java.nio.ByteBuffer;

public class NioTest5 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);
        }
        byteBuffer.position(2);
        byteBuffer.getShort(4);
        ByteBuffer slice = byteBuffer.slice();
        System.out.println(slice);
    }
}
