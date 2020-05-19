package classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        MyClassLoader myClassLoader = new MyClassLoader();
        Object o = myClassLoader.loadClass("classloader.ClassLoaderDemo").newInstance();
        System.out.println(o.getClass());
        System.out.println(o.getClass().getClassLoader());
        System.out.println(o instanceof ClassLoaderDemo);
    }
}


class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
        InputStream inputStream = getClass().getResourceAsStream(filename);
        if (inputStream == null) {
            return super.loadClass(name);
        }
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.loadClass(name);
    }
}