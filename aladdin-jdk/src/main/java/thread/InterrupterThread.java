package thread;

import org.junit.Test;

import java.util.UUID;

public class InterrupterThread {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("inddd");
                    return;
                }
            }
        });

        thread.start();

        thread.interrupt();
    }


    @Test
    public void testInterrupter_RunnableStateThread() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("线程被中断！ = " + Thread.currentThread().getName());
                        break;
                    }
                    System.out.println(UUID.randomUUID().toString());

                }
            }
        });

        thread.start();

        System.out.println("当前状态：" + thread.getState());
        System.out.println(thread.isInterrupted());

        Thread.sleep(100);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        while (thread.isInterrupted()) {
            System.out.println("已经中断");
        }
        System.out.println("中断后当前状态：" + thread.getState());


        Thread.sleep(100000);
    }

    @Test
    public void testInterrupter_BlockedThreadState() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockMethod();
            }
        });

        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockMethod();
            }
        });
        thread2.start();

        System.out.println("t1 state = " + thread1.getState());
        System.out.println("t2 state = " + thread2.getState());

        thread2.interrupt();

        System.out.println("t2 is interrupted = " + thread2.isInterrupted());
        System.out.println("t2 state = " + thread2.getState());
    }

    public synchronized void lockMethod() {
        while (true) {
        }
    }


    @Test
    public void testInterrupter_WaitingState(){

    }
}
