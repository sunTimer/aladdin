package thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo2 {

    public Semaphore semaphore = new Semaphore(5);


    public void run() throws InterruptedException {
        if (semaphore.tryAcquire()){
            System.out.println("thread:" + Thread.currentThread().getName() + ", ");
        }
        semaphore.acquire();
        System.out.println("running");
        while (true) {
            Random random = new Random();
            if (random.nextInt(100) > 1000) {
                break;
            }
        }
        semaphore.release();
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo2 semaphoreDemo2 = new SemaphoreDemo2();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphoreDemo2.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(10000);
    }
}
