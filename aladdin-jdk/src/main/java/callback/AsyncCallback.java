package callback;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class AsyncCallback {

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    public void getMapping(List<String> nameList) {
        executorService.execute(new Task(new Callback() {
            @Override
            public void execute(List<String> nameList) {
                System.out.println("4. execute :name list size =" + nameList.size());
            }
        }, nameList));
        System.out.println("1. getMapping is returned");
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            getMapping(new ArrayList<>());
        }
    }
}

class Task implements Runnable {

    private Callback callback;

    private List<String> nameList;

    public Task(Callback callback, List<String> nameList) {
        this.callback = callback;
        this.nameList = nameList;
    }

    @Override
    public void run() {
        System.out.println("2. run :name list size =" + nameList.size());
        for (int i = 0; i < 10; i++) {
            nameList.add(ThreadLocalRandom.current().nextInt() + "");
        }
        System.out.println("3. task is end");
        callback.execute(nameList);
    }
}

interface Callback {


    void execute(List<String> nameList);
}
