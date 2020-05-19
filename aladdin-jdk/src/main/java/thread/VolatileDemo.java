package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileDemo {

    public static boolean flag = true;

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(2);

        executors.execute(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(flag){
                    if (i++ / 10 == 0){
                        System.out.println("flag is true");
                    }
                }
                System.out.println("flag is false. end..");
            }
        });

        executors.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("curr flag is true");
                flag = false;
                System.out.println("curr flag is false");
            }
        });
    }
}
