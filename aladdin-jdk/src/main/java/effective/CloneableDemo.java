package effective;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

public class CloneableDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        Student src = new Student("shixu", 23);
        Student copy = src.clone();

        System.out.println(src.loves);
        System.out.println(copy.loves);

        System.out.println(src.homes);
        System.out.println(copy.homes);
    }
}

class Student implements Cloneable {

    String name;
    int age;
    final String address = "beijing";
    String[] loves;
    final Homes homes = new Homes();

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        loves = new String[5];
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {

        Student student = (Student) super.clone();
        student.loves = loves.clone();

        return student;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", loves=" + Arrays.toString(loves) +
                '}';
    }
}


class Homes implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}