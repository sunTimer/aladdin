package bytecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ByteCodeMain {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.loadClass("bytecoded.User");
        User o = (User) aClass.newInstance();
        System.out.println(o.getClass().getClassLoader());
        System.out.println(o);
        User user = new User();
        System.out.println(user.getClass().getClassLoader());

        try {
            lock.lock();
            user.setCode('d');
            user.size();
            lock.unlock();
            System.out.println(user.toString());
            int a = SubClass.a;
            SubClass.setA(3);
            if (a > 5) {
                SubClass.a = 5;
            } else {
                SubClass.a = 10;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

class StaticClass {
    static int a;

    static {
        a = 3;
    }

    public static void setA(int b) {
        a = b;
    }
}

class SubClass extends StaticClass {

}

class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String classFilename = name + ".class";
        File classFile = new File(classFilename);
        if (classFile.exists()) {
            try (FileChannel fileChannel = new FileInputStream(classFile)
                    .getChannel()) {
                MappedByteBuffer mappedByteBuffer = fileChannel
                        .map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
                byte[] b = mappedByteBuffer.array();
                clazz = defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }
}