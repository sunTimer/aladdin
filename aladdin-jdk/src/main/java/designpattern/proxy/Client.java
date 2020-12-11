package designpattern.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        GoodStudent goodStudent = new GoodStudent();
        StaticProxyStudent staticProxyStudent = new StaticProxyStudent(goodStudent);
        staticProxyStudent.giveTask();

        System.out.println("----------");

        GoodStudent goodStudent1 = new GoodStudent();
        InvocationHandler invocationHandler = new DynamicProxyStudent<Student>(goodStudent1);
        Student student = (Student) Proxy.newProxyInstance(DynamicProxyStudent.class.getClassLoader(), new Class<?>[]{Student.class}, invocationHandler);
        student.giveTask();

        System.out.println(student.getClass().getName());

        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Student.class.getInterfaces());
        String path = "/Users/shixu/IdeaProjects/aladdin/aladdin-jdk/src/main/java/designpattern/proxy/Proxy.class";

        try{
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        }catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
}

