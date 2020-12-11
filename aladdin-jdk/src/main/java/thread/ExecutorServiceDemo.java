package thread;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

    @Test
    public void test(){
        System.out.println(Integer.toBinaryString(-1 << 29));
    }

    @Test
    public void demo() throws InterruptedException {


        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date());
            }
        }, 5, 2, TimeUnit.SECONDS);



        Thread.sleep(100000);
    }
}
