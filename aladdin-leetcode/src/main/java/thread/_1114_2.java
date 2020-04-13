package thread;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1114_2 {

    Semaphore one = new Semaphore(0);
    Semaphore two = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        one.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        one.acquire();
        printSecond.run();
        two.release();

    }

    public void third(Runnable printThird) throws InterruptedException {
        two.acquire();
        printThird.run();
    }


    @Test
    public void test() throws InterruptedException {
        second(new Runnable() {
            @Override
            public void run() {
                System.out.println("hehe");
            }
        });
    }
}
