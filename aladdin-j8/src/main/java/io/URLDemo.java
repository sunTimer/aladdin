package io;


import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class URLDemo {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.baidu.com");
        InputStream inputStream = url.openStream();

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        byte[] buffer = new byte[100];
//
//        while (bufferedInputStream.read(buffer) != -1) {
//            String s = new String(buffer, StandardCharsets.UTF_8);
//            System.out.println(s);
//        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));

        System.out.println("===========");
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }


    }
}
