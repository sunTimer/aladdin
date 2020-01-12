package sort;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 最小堆，上面的节点比下面的节点都小
 */
public class HeapSort {

    /**
     * 整体逻辑：
     * 1. 构建堆：从最后一个非叶子节点开始，向上遍历，调整堆。直至遍历到第一个元素，即根节点，此时索引为1
     * 2. 排序：构建完堆以后，根节点肯定是最小的（本代码假设为最小堆），此时将根节点与最后一个元素交换，
     * 交换以后，就要重新调整堆了，从根节点开始调整，但此时堆的长度发生了变化，最后一个元素不参与调整，因此它是排好序的了
     *
     * @param nums
     */
    public void heapSort(int[] nums) {
        buildHeap(nums);
        int len = nums.length - 1;
        for (int i = len; i > 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i - 1);
        }
    }


    /**
     * 堆化的过程，即从最后一个非叶子节点开始上溯
     * 最后一个非叶子节点的位置为： （right - left） / 2
     * 7
     * /   \
     * 3     9
     * / \   / \
     * 12   5 1   6
     *
     * @param nums
     */
    public void heapify(int[] nums, int index, int len) {
        int l = leftIndex(index);
        int r = l + 1;

        if (l > len) {
            return;
        }

        int minPos = l;
        if (r <= len && nums[r] < nums[l]) {
            minPos = r;
        }

        if (nums[index] > nums[minPos]) {
            swap(nums, index, minPos);
            heapify(nums, minPos, len);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int leftIndex(int i) {
        return (i << 1) + 1;
    }

    public int rightIndex(int i) {
        return (i + 1) << 1;
    }

    public void buildHeap(int[] nums) {
        int start = (nums.length - 2) >> 1;

        for (int i = start; i >= 0; i--) {
            heapify(nums, i, nums.length - 1);
        }
    }

    @Test
    public void test() {
        int[] nums = new int[100 * 10000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ThreadLocalRandom.current().nextInt();
        }
        heapSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
