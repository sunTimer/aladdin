package sort;

import org.junit.Test;

public class BubbleSort {

    public void bubbleSort(int[] nums) {

        int i = 0;
        int n = nums.length - 1;
        while (i < n) {
            int j = i;
            while (j < n) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
                j++;
            }
            i = 0;
            n--;
        }
    }

    @Test
    public void test() {
        int[] nums = {7, 3, 2, 9, 8, 5, 4, 34, 23, 23, 3, 52, 632};
        bubbleSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
