package lock;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();


    public void write() {
        READ_WRITE_LOCK.writeLock().lock();

        System.out.println("writing.....");

        READ_WRITE_LOCK.writeLock().unlock();
    }

    public void read() {
    }
}
