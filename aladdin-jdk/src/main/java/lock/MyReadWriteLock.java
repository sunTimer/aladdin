package lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReadWriteLock {

    Lock readLock = new ReentrantLock();
    Lock writeLock = new ReentrantLock();

    Condition readCondition = writeLock.newCondition();
    Condition writeCondition = readLock.newCondition();

    boolean canRead = true;

    public static void main(String[] args) throws InterruptedException {
        MyReadWriteLock myReadWriteLock = new MyReadWriteLock();
        myReadWriteLock.read();
    }

    void read() throws InterruptedException {

        Semaphore semaphore = new Semaphore(-1);
        semaphore.acquire();
        System.out.println("hello");
        if (canRead) {

        }
    }

    void write() {

    }
}
