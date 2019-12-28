package decode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Test {

    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo("shixu", 1111);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println(b.length);
        bos.close();
        System.out.println(userInfo.code().length);
        System.out.println(new String(userInfo.code()));
        System.out.println(new String(b));
    }
}
