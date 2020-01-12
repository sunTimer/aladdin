package sort;

import org.junit.Test;

/**
 * @author shixu
 */
public class QuikSort {

    public void quickSort(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        partition(nums, left, right);
    }

    private void partition(int[] nums, int left, int right) {
        if (right - left < 1) {
            return;
        }
        int pivot = nums[right];

        int l = left;
        int r = right;

        while (l < r) {
            while (nums[l] < pivot) {
                l++;
            }
            while (nums[r] >= pivot) {
                r--;
            }
            swap(nums, l, r);
        }
        partition(nums, left, l - 1);
        partition(nums, l + 1, right);
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    @Test
    public void test() {
        int[] nums = {4, 2, 1, 5, 12, 3, 12, 52};
        quickSort(nums);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }
}
