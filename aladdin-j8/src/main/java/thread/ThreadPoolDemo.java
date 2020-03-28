package thread;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                3,
                500,
                TimeUnit.DAYS,
                new ArrayBlockingQueue<>(100),
                (ThreadFactory) Thread::new,
                (r, executor) -> {
                    // null
                }

        );

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ff");
            }
        });
    }


    public void fixThreadPool(){
        // core, max = core, 0,
        Executors.newFixedThreadPool(3);
    }

    public void singleThreadPool(){
        Executors.newSingleThreadExecutor();
    }
}