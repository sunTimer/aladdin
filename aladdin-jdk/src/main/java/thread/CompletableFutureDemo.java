package thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String s = CompletableFuture.supplyAsync(() -> run1())
                .thenApply(i -> add(i))
                .thenApply(i -> add(i))
                .exceptionally(e -> excep())
                .thenApply(i -> print(i)).get();

        System.out.println(s);
    }

    public static int excep() {
        return 1;
    }

    public static int run1() {
        return 1;
    }

    public static int add(int i) {
        if(i > 0){
            throw new IllegalArgumentException("" + i);
        }
        return i + 1;
    }

    public static String print(int i) {
        System.out.println(i);
        return "success";
    }
}
