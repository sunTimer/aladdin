package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandleThreadExceptionDemo {

    public static void main(String[] args) {

        ExecutorService exec =
                Executors.newSingleThreadExecutor();
        exec.submit(() -> {
            throw new RuntimeException();
        });
        exec.shutdown();


        Thread thread = new Thread(new ThrowExceptionRunnable());
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("" + e);
            }
        });
        thread.start();


        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new ThrowExceptionRunnable());

        executorService.submit(new ThrowExceptionRunnable());
    }
}

class ThrowExceptionRunnable implements Runnable{

    @Override
    public void run() {
        throw new IllegalArgumentException();
    }
}
