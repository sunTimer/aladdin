package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _703 {
}

class KthLargest {

    private PriorityQueue<Integer> queue;

    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<>(k, Comparator.comparingInt(o -> o));
        for (int i = 0; i < k && i < nums.length; i++) {
            queue.add(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (queue.peek() < val) {
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 2, 1213, 42};
        KthLargest kthLargest = new KthLargest(3, nums);
        int max = kthLargest.add(23);
        System.out.println(max);
    }
}