package lambda;

import sun.misc.Unsafe;

import java.util.function.Consumer;

public class Test {

    public static void main(String[] args) throws InstantiationException {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("heelo");
        Unsafe unsafe = Unsafe.getUnsafe();
        Object o = unsafe.allocateInstance(Test.class);
        System.out.println(o);
    }
}
