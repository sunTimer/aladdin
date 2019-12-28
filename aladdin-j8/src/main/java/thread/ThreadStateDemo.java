package thread;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class ThreadStateDemo {


    //---------------------------thread is new state.--------------------------------------------
    @Test
    public void testThread_StateIsNew() {
        Thread thread = new Thread(() -> {
        });
        Assert.assertEquals("NEW", thread.getState().name());
    }

    //--------------------thread is runnable.--------------------------------------------------
    @Test
    public void testThread_StateIsRunnable() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals("RUNNABLE", Thread.currentThread().getState().name());
            }
        });

        thread.start();
    }

    //-----------------------thread is terminated.-------------------------------------------------------
    @Test
    public void testThread_StateIsTerminated() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doSomething();
            }
        });
        thread.start();
        thread.join();
        Assert.assertEquals("TERMINATED", thread.getState().name());
    }


    //------------------------thread is blocked due to waiting a monitor lock.-------------------------------------------------------
    private Thread thread2;

    @Test
    public void testThread_StateIsBlocked() {
        Thread thread1;
        Thread thread2;
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockMethod();
            }
        });

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                doSomething();
                lockMethod();
            }
        });

        thread1.start();
        thread2.start();
    }

    private synchronized void lockMethod() {
        doSomething();
        Assert.assertEquals("BLOCKED", thread2.getState().name());
    }

    private void doSomething() {
        Random random = new Random();
        for (long i = 0L; i < 8000000L; i++) {
            random.nextInt();
        }
    }

    //------------------thread is waiting state.--------------------------------------------------------
    @Test
    public void testThread_StateIsWaiting_1() throws IOException, InterruptedException {

        // 通过join的方式，运行不稳定。

        Thread childThread = new Thread(new Runnable() {
            @Override
            public void run() {
                doSomething();
            }
        }, "childThread");

        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    childThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "mainThread");


        mainThread.start();
        childThread.start();

        Thread.sleep(10000);
        Assert.assertEquals("WAITING", mainThread.getState().name());
    }

    @Test
    public void testThread_StateIsWaiting_2() throws IOException {

        // wait notify 实现获取线程waiting状态

        // 线程在调用对象的wait和notify方法时，必须获得该对象的monitor lock。
        // 在调用wait之后当前线程释放lock，在其他线程调用该对象的notifyAll方法以后，该线程被唤醒
        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " 被唤醒.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("t1 当前状态：" + t1.getState().name());
                    Assert.assertEquals("WAITING", t1.getState().name());
                    lock.notifyAll();
                    System.out.println(Thread.currentThread().getName() + " 发出了通知.");
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        doSomething();
    }


    //----------------------time_waiting state---------------------------------------------------------------------
    @Test
    public void testThread_StateIsTimeWaiting() {

        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        lock.wait(100);
                        System.out.println(Thread.currentThread().getName() + " 被唤醒.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("t1 当前state：" + t1.getState().name());
                    Assert.assertEquals("TIMED_WAITING", t1.getState().name());
                    lock.notifyAll();
                    System.out.println(Thread.currentThread().getName() + " 发出了通知.");
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        doSomething();
    }
}
