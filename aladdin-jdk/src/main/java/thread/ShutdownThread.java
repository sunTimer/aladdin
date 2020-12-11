package thread;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownThread {

    @Test
    public void testShutdown() throws IOException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {

                    System.out.println(Thread.currentThread().getName());
                }
            }
        };
        executor.execute(runnable);

        executor.execute(runnable);

        executor.execute(runnable);

        executor.shutdownNow();

        Thread.sleep(10000);
    }

}
