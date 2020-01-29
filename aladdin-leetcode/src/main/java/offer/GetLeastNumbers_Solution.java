package offer;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class GetLeastNumbers_Solution {

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {

        if (k > input.length) {
            return new ArrayList<>();
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            priorityQueue.add(input[i]);
        }

        for (int i = k; i < input.length; i++) {
            if (priorityQueue.peek() > input[i]) {
                priorityQueue.poll();
                priorityQueue.add(input[i]);
            }
        }

        return new ArrayList<>(priorityQueue);
    }

    @Test
    public void test() {
        int[] input = {4, 5, 1, 6, 2, 7, 3, 8};
        System.out.println(GetLeastNumbers_Solution(input, 10));
    }
}
