package serialize;

import lombok.ToString;
import org.junit.Test;

import java.io.*;
import java.util.Random;

public class SerializingDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int i = -3;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i << 2));
        System.out.println(Integer.toBinaryString(i >> 2) + "," + (i >> 2));

        System.out.println(Integer.toBinaryString(21));
        System.out.println(Integer.toBinaryString(21 << 32));

        System.out.println(Integer.toBinaryString(21 >> 33));
        System.out.println(Integer.toBinaryString(21 >> 1));
        serialize(new User("shixu", "beijing"));
        deserialize();
    }

    public static void serialize(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.txt"));
        oos.writeObject(obj);
        oos.close();
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.txt"));
        User user = (User) ois.readObject();
        System.out.println(user);
    }

    public void copyij(int[][] src, int[][] dest) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < dest.length; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }

    public void copyji(int[][] src, int[][] dest) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < dest.length; j++) {
                dest[j][i] = src[j][i];
            }
        }
    }

    @Test
    public void testCopy() {
        Random random = new Random();
        int len = 5000;
        int[][] src =new int[len][len];
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                src[i][j] = random.nextInt(3000);
            }
        }

        int[][] dest = new int[len][len];
        long start = System.currentTimeMillis();
        copyij(src, dest);
        System.out.println("cost copyij:" + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
        copyji(src, dest);
        System.out.println("cost copyji:" + (System.currentTimeMillis() - start));

    }
}

@ToString
class User implements Serializable {

    public User(String name, String address) {
        this.name = name;
        this.address = address;
        System.out.println("constructor-----");
    }

    private String name;
    private String address;
}

