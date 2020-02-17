package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDemo {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/shixu/IdeaProjects/aladdin/aladdin-j8/src/main/java/io/URLDemo.java");
        FileInputStream fileInputStream = new FileInputStream(file);

        System.out.println(fileInputStream.getFD().valid());
    }
}
