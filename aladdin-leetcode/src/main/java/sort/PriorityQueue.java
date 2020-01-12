package sort;

import org.junit.Assert;
import org.junit.Test;

public class PriorityQueue {

    int[] data;
    int size;
    int maxSize;

    public PriorityQueue() {
        this.size = 0;
        this.maxSize = 10;
        data = new int[maxSize];
    }

    public void insert(int e) {
        if (size == maxSize) {
            throw new IllegalArgumentException("queue is filled.");
        }
        data[size++] = e;
        siftUp(size - 1);
    }

    public void heapify(int[] nums) {
        if (nums.length > maxSize) {
            throw new IllegalArgumentException("nums is too long.");
        }
        for (int num : nums) {
            insert(num);
        }
    }

    public void heapSort() {
        if (size == 0) {
            return;
        }
    }

    public int getMax() {
        return data[0];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("index illegal.");
        }

        data[i] = Integer.MAX_VALUE;
        siftUp(i);
        extractMax();
    }

    public int extractMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty.");
        }
        int max = data[0];
        data[0] = data[--size];
        siftDown(0);
        return max;
    }

    private void siftDown(int i) {
        if (i >= size) {
            return;
        }

        int l = left(i);
        int r = right(i);

        int max = i;

        if (l <= size - 1 && data[l] > data[max]) {
            max = l;
        }
        if (r <= size - 1 && data[r] > data[max]) {
            max = r;
        }

        swap(i, max);
        if (max != i) {
            siftDown(max);
        }
    }


    private void siftUp(int i) {
        if (i < 0) {
            return;
        }

        int l = left(i);
        int r = right(i);


        int max = i;
        if (l <= size - 1 && data[l] > data[max]) {
            max = l;
        }
        if (r <= size - 1 && data[r] > data[max]) {
            max = r;
        }

        swap(i, max);
        siftUp(parent(i));
    }

    private void swap(int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private void print() {
        for (int i : data) {
            System.out.print(" " + i);
        }
    }

    private int parent(int i) {
        return (i - 1) >> 1;
    }

    private int left(int i) {
        return (i << 1) + 1;
    }

    private int right(int i) {
        return (i + 1) << 1;
    }


    @Test
    public void testGetMax() {
        insert(20);
        insert(12);
        insert(523);
        insert(-1);
        Assert.assertEquals(523, getMax());
    }

    @Test
    public void testRemove() {
        insert(20);
        insert(12);
        insert(523);
        insert(-1);
        remove(0);
        Assert.assertEquals(20, getMax());
    }

    @Test
    public void testHeapify() {
        int[] nums = {20, 12, 523, -1};
        heapify(nums);
        Assert.assertEquals(523, getMax());
    }
}