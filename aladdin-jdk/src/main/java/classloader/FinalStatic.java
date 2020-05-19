package classloader;

public class FinalStatic {
    public static void main(String[] args) {
        // final static 字段不会触发类的初始化
        System.out.println(Student.NAME);

        // static字段的读写、static方法的调用会触发类的初始化
        // 类的初始化和对象的实例化要分清
        System.out.println(Student.age);
        System.out.println(Student.address);

//        System.out.println(Student.getAge());
        int[][][][][][][][] a = new int[1][1][2][2][2][2][2][2];
    }
}


class Student {
    Student() {
        System.out.println("构造方法");
    }

    {
        System.out.println("...");
    }

    static int age;
    static String address;
    static final String NAME = "shixu";
    static {
//        age = 10;
        address = "dd";
        System.out.println("静态代码段");
        System.out.println(address);
       if (true){
           System.out.println("wow+" + Thread.currentThread().getName());
           while (true){
           }
       }
    }


    public static int getAge(){
        return age;
    }
}