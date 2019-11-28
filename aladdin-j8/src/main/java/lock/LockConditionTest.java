package lock;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {

    public static void main(String[] args) {
        MyQueue<String> byCondition = new BoundQueueByCondition<>();
        MyQueue<String> bySynchronized = new BoundQueueBySynchronized<>();

        test(byCondition);
        test(bySynchronized);
    }


    private static void test(MyQueue<String> queue) {
        int n = 100;
        Thread[] putThreads = new Thread[n];
        Thread[] removeThreads = new Thread[n];

        // init threads
        for (int i = 0; i < n; i++) {
            putThreads[i] = new Thread(() -> {
                try {
                    queue.put(Thread.currentThread().getName() + " shixu");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            removeThreads[i] = new Thread(() -> {
                try {
                    System.out.println(queue.remove());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // start threads
        for (int i = 0; i < n; i++) {
            putThreads[i].start();
            removeThreads[i].start();
        }
    }
}

// by lock and condition
class BoundQueueByCondition<E> implements MyQueue<E> {
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    private int size = 5;
    private Queue<E> queue;

    public BoundQueueByCondition(int size) {
        this.size = size;
        this.queue = new ArrayDeque<>(size);
    }

    public BoundQueueByCondition() {
        this.queue = new ArrayDeque<>(size);
    }

    @Override
    public void put(E e) throws InterruptedException {
        lock.lock();
        while (queue.size() == size) {
            System.out.println(Thread.currentThread().getName() + " is blocked. Because " + "queue is full");
            notEmpty.await();
        }
        queue.add(e);
        notFull.signal();
        lock.unlock();
    }

    @Override
    public E remove() throws InterruptedException {
        lock.lock();
        while (queue.size() == 0) {
            System.out.println(Thread.currentThread().getName() + " is blocked. Because " + "queue is empty.");
            notFull.await();
        }
        E e = queue.remove();
        notEmpty.signal();
        lock.unlock();
        return e;
    }
}


// by synchronized and monitor
class BoundQueueBySynchronized<E> implements MyQueue<E> {
    private final Object lock = new Object();
    private int size = 5;
    private Queue<E> queue;

    public BoundQueueBySynchronized(int size) {
        this.size = size;
        this.queue = new ArrayDeque<>(size);
    }

    public BoundQueueBySynchronized() {
        this.queue = new ArrayDeque<>(size);
    }


    @Override
    public void put(E e) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == size) {
                System.out.println(Thread.currentThread().getName() + " is blocked. Because " + "queue is full");
                lock.wait();
            }
            queue.add(e);
            lock.notify();
        }
    }

    @Override
    public E remove() throws InterruptedException {
        E e;
        synchronized (lock) {
            while (queue.size() == 0) {
                System.out.println(Thread.currentThread().getName() + " is blocked. Because " + "queue is empty.");
                lock.wait();
            }
            e = queue.remove();
            lock.notify();
        }
        return e;
    }
}


interface MyQueue<E> {
    void put(E e) throws InterruptedException;

    E remove() throws InterruptedException;
}