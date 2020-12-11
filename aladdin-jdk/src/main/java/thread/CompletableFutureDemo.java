package thread;

import lombok.ToString;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * 参考：https://colobu.com/2018/03/12/20-Examples-of-Using-Java%E2%80%99s-CompletableFuture/
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String s = CompletableFuture.supplyAsync(() -> run1())
                .thenApply(i -> add(i))
                .thenApply(i -> add(i))
                .exceptionally(e -> excep())
                .thenApply(i -> print(i)).get();

        System.out.println(s);


        CompletableFuture.runAsync(() -> {
            fetchData();
        }).thenApply((Function<Void, Object>) aVoid -> checkData(50));


    }

    public static int excep() {
        return 1;
    }

    public static int run1() {
        return 1;
    }

    public static int add(int i) {
        if (i > 0) {
            throw new IllegalArgumentException("" + i);
        }
        return i + 1;
    }

    public static String print(int i) {
        System.out.println(i);
        return "success";
    }

    public static int fetchData() {
        System.out.println("开始拉取数据。。。。");
        System.out.println("数据拉取完毕，共50条。");

        return 50;
    }

    public static int checkData(int size) {
        System.out.println("检查数据");
        return 50;
    }

    @Test
    public void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }

    @Test
    public void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            System.out.println(Thread.currentThread().getName());
            randomSleep();
        });
        assertFalse(cf.isDone());
        sleepEnough();
        assertTrue(cf.isDone());
    }

    @Test
    public void thenApplyExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow("null"));
    }

    @Test
    public void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            System.out.println(Thread.currentThread().getName());
            randomSleep();
            return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    @Test
    public void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s -> result.append(s));

        assertTrue("Result was empty", result.length() > 0);

        System.out.println(result.toString());
    }

    void randomSleep() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    void sleepEnough() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @Test
    public void order() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Set<Order> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            CompletableFuture.supplyAsync(() -> getOrder(finalI))
                    .thenApply(order -> enrichOrder(order))
                    .thenApply(order -> performancePayment(order))
                    .thenApply(order -> dispatch(order))
                    .exceptionally(order -> failOrder(order))
                    .thenApply(order -> sendMail(order))
                    .thenAccept(order -> set.add(order));
        }
        System.out.println(set);
    }

    private Order failOrder(Throwable e) {
        System.out.println(e);
        return new Order(100);
    }

    private Order sendMail(Order order) {
        //System.out.println(Thread.currentThread().getName() + " send mail ok");
        int i = 3 / 0;

        return order;
    }

    Order dispatch(Order order) {
        //System.out.println(Thread.currentThread().getName() + " dispatch ok");
        return order;
    }

    Order performancePayment(Order order) {
        //System.out.println(Thread.currentThread().getName() + " payment ok");
        return order;
    }

    Order enrichOrder(Order order) {
        //System.out.println(Thread.currentThread().getName() + " enrich order ok");
        return order;
    }

    Order getOrder(int i) {
        //System.out.println(Thread.currentThread().getName() + " get order ok");
        return new Order(i);
    }
}


@ToString
class Order {


    private int id;

    public Order(int id) {
        this.id = id;
    }
}