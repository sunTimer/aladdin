package thread;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    int count = 0;
    Semaphore semaphore = new Semaphore(1);

    private int incr() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }

        return incr2();
    }

    private int incr2() {
        return count++;
    }

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();

        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(semaphoreDemo.incr2() + Thread.currentThread().getName());
                }
            }).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
