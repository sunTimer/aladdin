package sort;

public class MaxHeap {

    int[] nums;
    int heapSize;
    int len;

    /*
    <pre>
         42
       32  12
     5   25
    </pre>
     */
    public static void main(String[] args) {
        int[] nums = {32, 42, 12, 5, 25};
        MaxHeap maxHeap = new MaxHeap(nums);
        maxHeap.heapSort();
    }

    public MaxHeap(int[] nums) {
        this.nums = nums;
        this.heapSize = 0;
        this.len = nums.length;
    }

    public void heapSort() {

        heapify();

        for (int i = 0; i < len; i++) {
            delete();
        }

        for (int num : nums) {
            System.out.println(num);
        }
    }

    public void heapify() {
        this.heapSize = len;
        for (int i = len - 1; i >= 0; i--) {
            down(i);
        }
    }

    public void insert(int e) {
        if (heapSize == len) {
            throw new IllegalArgumentException("heap is full");
        }
        nums[heapSize++] = e;
        up(heapSize - 1);
    }

    public void delete() {
        if (heapSize == 0) {
            throw new IllegalArgumentException("heap is empty");
        }
        swap(nums, 0, --heapSize);
        down(0);
    }

    /**
     * 10
     * 8  4
     * 12
     * 上旋
     */
    public void up(int i) {
        if (i > 0 && nums[parent(i)] < nums[i]) {
            swap(nums, i, parent(i));
            up(parent(i));
        }
    }

    /**
     * 3
     * 8  4
     * 12
     * 下钻
     */
    public void down(int i) {
        if (i < heapSize) {
            int nextIndex = i;
            if (nums[nextIndex] < nums[left(i)]) {
                nextIndex = left(i);
            }
            if (nums[nextIndex] < nums[right(i)]) {
                nextIndex = right(i);
            }
            if (nextIndex != i) {
                swap(nums, i, nextIndex);
                down(nextIndex);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * <pre>
     *      0
     *    /   \
     *    1    2
     *   / \
     *  3   4
     * </pre>
     *
     * @param i i
     * @return index
     */
    public int left(int i) {
        return (i << 1) + 1;
    }

    public int right(int i) {
        return (i + 1) << 1;
    }

    public int parent(int i) {
        return i >> 1;
    }
}
