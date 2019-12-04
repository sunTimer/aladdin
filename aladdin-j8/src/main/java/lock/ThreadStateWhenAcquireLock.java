package lock;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadStateWhenAcquireLock {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread t1;
        Thread t2;
        t1 = new Thread(() -> {
            sharedResource.synchronizedMethod();
        });
        t1.setName("t1");

        t2 = new Thread(() -> {
            sharedResource.synchronizedMethod();
        });
        t2.setName("t2");

        t1.start();
        t2.start();

        boolean notAllTerminated = !t1.getState().name().equals("TERMINATED")
                || !t2.getState().name().equals("TERMINATED");
        while (notAllTerminated) {
            System.out.printf("我是：%s，当前t1的状态是：%s，t2的状态是：%s，当前运行到索引：%d. 当前是：%s:\n",
                    Thread.currentThread().getName(), t1.getState(),
                    t2.getState(), sharedResource.getIndex(), LocalDateTime.now());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("t1 and t2 died.");
    }
}


class SharedResource {

    private int index = 0;
    private Lock lock = new ReentrantLock();

    int getIndex() {
        return index;
    }

    void synchronizedMethod() {
        lock.lock();
        doSomething();
        lock.unlock();
    }

    private void doSomething() {
        System.err.printf("我是：%s，我获取到了锁哦，不信看我的状态：%s. 当前是：%s\n"
                , Thread.currentThread().getName()
                , Thread.currentThread().getState()
                , LocalDateTime.now());
        for (; index < 100000; index++) {
            // just for use cpu.
            String uuid = UUID.randomUUID().toString();
        }
        System.err.printf("我是：%s，我干完活啦，不信看我的状态：%s. 当前是：%s\n"
                , Thread.currentThread().getName()
                , Thread.currentThread().getState()
                , LocalDateTime.now());
        index = 0;
    }
}