package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println("running start=" + System.currentTimeMillis());

        executorService.submit(new DependentService(countDownLatch));
        executorService.submit(new DependentService(countDownLatch));
        executorService.submit(new DependentService(countDownLatch));

        countDownLatch.await();
        System.out.println("running end=" + System.currentTimeMillis());
    }
}


class DependentService implements Runnable {

    private CountDownLatch latch;

    public DependentService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        // do something
        latch.countDown();
    }
}
