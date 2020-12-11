package lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentryLockDemo {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        if(reentrantLock.tryLock()) {
            reentrantLock.lock();
        }
    }
}
