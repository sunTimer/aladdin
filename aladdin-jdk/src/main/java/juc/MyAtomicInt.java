package juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyAtomicInt {

    private volatile int value;

    private static Unsafe unsafe;

    private static long valueOffset;

    static {
        Field f;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            valueOffset = unsafe.objectFieldOffset(MyAtomicInt.class
                    .getDeclaredField("value"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public int get() {
        return value;
    }

    public MyAtomicInt() {
        this.value = 0;
    }

    public int incrementAndGet() {
        while (true) {
            int expect = unsafe.getIntVolatile(this, valueOffset);
            if (unsafe.compareAndSwapInt(this, valueOffset, expect, expect + 1)) {
                return expect;
            }
            System.err.println("error:" + expect + ":" + unsafe.getIntVolatile(this, valueOffset));
        }
    }

    public static void main(String[] args) {
        MyAtomicInt myAtomicInt = new MyAtomicInt();

        ExecutorService executorService = Executors.newFixedThreadPool(200);

        for (int i = 0; i < 10000000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    myAtomicInt.incrementAndGet();
                }
            });
        }
    }
}
