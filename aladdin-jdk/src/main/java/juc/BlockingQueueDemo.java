package juc;

import org.junit.Assert;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10000);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int n = 50;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            executorService.execute(new Task(queue, countDownLatch));
        }

        countDownLatch.await();
        System.out.println(queue.size());
        Assert.assertEquals(n * 100, queue.size());
    }
}

class Task implements Runnable {

    ArrayBlockingQueue<Integer> queue;
    CountDownLatch countDownLatch;

    public Task(ArrayBlockingQueue<Integer> queue, CountDownLatch countDownLatch) {
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.queue.add(i);
        }
        this.countDownLatch.countDown();
    }
}
