package thread;

import org.junit.Test;

import java.util.concurrent.*;

public class FutureTaskDemo {

    @Test
    public void future() throws Exception {
        Callable<Integer> task = () -> ThreadLocalRandom.current().nextInt();

        ExecutorService executors = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Future<Integer> ret = executors.submit(task);
            System.out.println(ret.isDone());
            Integer x = ret.get();
            System.out.println(ret.isDone());
            System.out.println(x);
        }
    }
}
