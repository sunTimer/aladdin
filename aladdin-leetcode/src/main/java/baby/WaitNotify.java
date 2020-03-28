package baby;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 启动两个线程, 一个输出 1,3,5,7…99, 另一个输出 2,4,6,8…100 最后 STDOUT 中按序输出 1,2,3,4,5…100
 */
public class WaitNotify {

    static int i = 1;
    static Lock lock = new ReentrantLock();
    static Condition a = lock.newCondition();
    static Condition b = lock.newCondition();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                while (i < 100) {
                    if (i % 2 == 0) {
                        try {
                            a.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + i++);
                    b.signal();
                }
                lock.unlock();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                while (i <= 100) {
                    if (i % 2 != 0) {
                        try {
                            b.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + i++);
                    a.signal();
                }
                lock.unlock();
            }
        }).start();
    }
}
