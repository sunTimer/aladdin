package thread;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

    @Test
    public void demo() throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 5, 2, TimeUnit.SECONDS);

        Thread.sleep(100000);
    }
}
