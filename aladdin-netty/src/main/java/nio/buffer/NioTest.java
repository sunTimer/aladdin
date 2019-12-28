package nio.buffer;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            int randomN = new SecureRandom().nextInt();
            intBuffer.put(randomN);
        }
        System.out.println("limit:" + intBuffer.limit());
        intBuffer.flip();
        System.out.println("limit:" + intBuffer.limit());
        intBuffer.clear();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
