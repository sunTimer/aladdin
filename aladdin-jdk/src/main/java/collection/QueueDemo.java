package collection;

import org.junit.Test;

import java.util.PriorityQueue;

public class QueueDemo {

    @Test
    public void testPriorityQueue() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        priorityQueue.add(43);
        priorityQueue.add(-2);
        priorityQueue.add(34);
        priorityQueue.add(23);
        System.out.println(priorityQueue);

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());;
        }
    }
}
