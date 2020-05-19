package thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class ForkJoinDemo {

    public static void main(String[] args) {
        int n = 10;
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = ThreadLocalRandom.current().nextInt();
        }
        nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        SumTask sumTask = new SumTask(0, n - 1, nums);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(sumTask);
        System.out.println(sumTask.join());
    }

}

class SumTask extends RecursiveTask<Integer> {

    private int[] src;
    private int from;
    private int to;

    private int seg = 10;

    private int threshold;

    public SumTask(int from, int to, int[] src) {
        this.from = from;
        this.to = to;
        this.src = src;
        this.threshold = src.length / seg;
    }

    @Override
    protected Integer compute() {
        if ((to - from) < threshold) {
            int count = 0;
            for (int i = from; i <= to; i++) {
                count += src[i];
            }
            return count;
        } else {
            int mid = (from + to) / 2;
            SumTask left = new SumTask(from, mid, src);
            left.fork();
            SumTask right = new SumTask(mid + 1, to, src);
            right.fork();
            return left.join() + right.join();
        }
    }
}