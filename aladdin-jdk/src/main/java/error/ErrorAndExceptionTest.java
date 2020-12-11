package error;

import org.junit.Test;


public class ErrorAndExceptionTest {

    /**
     * 类加载时，没有找到类的class文件
     *
     * @throws ClassNotFoundException
     */
    @Test(expected = ClassNotFoundException.class)
    public void testClassNotFound() throws ClassNotFoundException {
        Class.forName("no.found.class");
    }


    /**
     * NoClassDefFoundError 编译时存在，但运行时不存在，没有找到class
     *
     * @param args
     */
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a);
    }


    @Test
    public void testFinally() {
        try {
            System.out.println("he");
            System.exit(1);
        } finally {
            System.out.println("heheda¬");
        }
    }
}

class A {

}
