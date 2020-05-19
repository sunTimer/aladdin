package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private static final Lock LOCK = new ReentrantLock();

    private static int i = 0;

    private static void accessSharedResource() {
        LOCK.lock();
        i = i+1;
        LOCK.unlock();
    }

    public static void main(String[] args) throws InterruptedException {

        for (int j = 0; j < 100000; j++) {
            new Thread(LockDemo::accessSharedResource).start();
        }
        Thread.sleep(1000);
        System.out.println(i);
    }
}
