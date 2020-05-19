package lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadStateDemo {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                acqiure();
//                acquireByLock();
            }
        });

        thread1.setName("th-1");


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                acqiure();
//                acquireByLock();
            }
        });

        thread2.setName("th-2");

        thread1.start();
        thread2.start();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10);
                    System.out.println("th1 = " + thread1.getState());
                    System.out.println("th2 = " + thread2.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public synchronized static void acqiure() {
        int i = 1000000;
        while (i > 0) {
            Random random = new Random();
            random.nextInt();
            i--;
        }
    }

    private static Lock lock = new ReentrantLock();

    public static void acquireByLock() {
        lock.lock();
        int i = 1000000000;
        while (i > 0) {
            Random random = new Random();
            random.nextInt();
            i--;
        }
        lock.unlock();
    }
}
