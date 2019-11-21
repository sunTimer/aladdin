package nio.buffer;

import java.nio.ByteBuffer;

/**
 * 只读buffer
 */
public class NioTest6 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println(byteBuffer.getClass());
        for (int i = 0; i < 10; i++) {
            byteBuffer.put((byte) i);
        }

        System.out.println(byteBuffer.asReadOnlyBuffer().getClass());
    }
}
