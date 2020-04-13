package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1115_Foobar {

    private int n;

    private Lock lock = new ReentrantLock();
    private boolean flag = true;
    private Condition condition = lock.newCondition();

    public _1115_Foobar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            while (!flag) {
                condition.await();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            flag = false;
            condition.signal();
            lock.unlock();

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            while (flag) {
                condition.await();
            }
            printBar.run();
            flag = true;
            condition.signal();
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        _1115_Foobar foobar = new _1115_Foobar(10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foobar.foo(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("foo");
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
                    foobar.bar(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("bar");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}


