package juc;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        System.out.println(atomicInteger.incrementAndGet());
    }
}
