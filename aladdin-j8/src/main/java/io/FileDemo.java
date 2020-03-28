package io;

import java.io.*;
import java.util.Date;
import java.util.Random;

public class FileDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        writeToFile();
    }

    private static void fileDesc() throws IOException {
        File file = new File("/Users/shixu/IdeaProjects/aladdin/aladdin-j8/src/main/java/io/URLDemo.java");
        FileInputStream fileInputStream = new FileInputStream(file);
        // 😌
        System.out.println(fileInputStream.getFD());
    }

    private static void deleteFile() throws IOException {
        Random random = new Random();
        int i = random.nextInt();
        if (i % 2 == 0) {
            File file = new File("/Users/shixu/IdeaProjects/aladdin/aladdin-j8/src/main/java/io/test");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                byte[] b = new byte[1024];
                if (fileInputStream.read(b) <= 0) break;
                System.out.println(new String(b));
            }
        }
    }

    public static void writeToFile() throws IOException, InterruptedException {
        File file = new File("/Users/shixu/IdeaProjects/aladdin/aladdin-j8/src/main/java/io/test");


        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (int i = 0; i < 2000; i++) {
            fileOutputStream.write((new Date().toString() + "\n").getBytes());
            fileOutputStream.flush();
            Thread.sleep(1000);
        }
        fileOutputStream.close();
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            byte[] b = new byte[1024];
            if (fileInputStream.read(b) <= 0) break;
            System.out.println(new String(b));
        }
    }
}
