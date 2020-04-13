package thread;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1114 {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private int flag = 1;

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        while (flag != 1) {
            condition.await();
        }
        printFirst.run();
        flag = 2;
        condition.signal();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        while (flag != 2) {
            condition.await();
        }
        printSecond.run();
        flag = 3;
        condition.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        while (flag != 3) {
            condition.await();
        }
        printThird.run();
        flag = 1;
        condition.signal();
        lock.unlock();
    }


    @Test
    public void test() throws InterruptedException {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    third(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("third");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   first(new Runnable() {
                       @Override
                       public void run() {
                           System.out.println("one");
                       }
                   });
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    second(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("two");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
