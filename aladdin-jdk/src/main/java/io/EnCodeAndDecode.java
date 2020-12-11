package io;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class EnCodeAndDecode {

    public static void main(String[] args) {
        encode();
    }

    public static void encode() {
        String name = "I am 淘宝";

        byte[] bytes = name.getBytes(StandardCharsets.UTF_16BE);

        String s = new String(bytes, StandardCharsets.UTF_16BE);
        System.out.println(s);

        for (byte aByte : name.getBytes(StandardCharsets.UTF_8)) {
            System.out.println(Integer.toHexString(aByte));
        }
        try {
            byte[] iso8859 = name.getBytes("ISO-8859-1");
            toHex(iso8859);
            byte[] gb2312 = name.getBytes("GB2312");
            toHex(gb2312);
            byte[] gbk = name.getBytes("GBK");
            toHex(gbk);
            byte[] utf16 = name.getBytes("UTF-16");
            toHex(utf16);
            byte[] utf8 = name.getBytes("UTF-8");
            toHex(utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void toHex(byte[] utf8) {
        for (byte b : utf8) {
            System.out.print(Integer.toHexString(b) + "-");
        }
        System.out.println("==" + utf8.length);
    }
}
