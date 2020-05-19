package juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {

    static Unsafe unsafe;

    static {
        Field f;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            long nameOffset = unsafe.objectFieldOffset
                    (Student.class.getDeclaredField("name"));

            long ageOffset = unsafe.objectFieldOffset
                    (Student.class.getDeclaredField("age"));

            long addressOffset = unsafe.objectFieldOffset
                    (Student.class.getDeclaredField("address"));

            long idOffset = unsafe.objectFieldOffset
                    (Student.class.getDeclaredField("id"));

            long seqOffset = unsafe.objectFieldOffset
                    (Student.class.getDeclaredField("seq"));
            System.out.println(nameOffset);
            System.out.println(ageOffset);
            System.out.println(addressOffset);
            System.out.println(idOffset);
            System.out.println(seqOffset);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws NoSuchFieldException {
        Student student = new Student();
        student.name = "shixu";
        long nameOffset = unsafe.objectFieldOffset
                (Student.class.getDeclaredField("name"));
        System.out.println(unsafe.compareAndSwapObject(student, nameOffset, "shixu", "shixuxu"));
        System.out.println(student.name);
    }
}

class Student {
    String name;
    int age;
    int address;
    long id;
    int seq;
}
