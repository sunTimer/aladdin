import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str1 = "中文";
        byte[] bytes = str1.getBytes(StandardCharsets.UTF_16BE);
        String str2 = new String(bytes, StandardCharsets.UTF_16BE);
        System.out.println(str2);
        System.out.println(bytes.length);
    }
}
