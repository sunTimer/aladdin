package sort;

import org.junit.Test;

import java.util.List;

public class QuickSort2 {

    @Test
    public void test() {
        int[] nums = {4, 2, 15, 56, 1};
        quickSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int p = partition(nums, left, right);
            quickSort(nums, left, p - 1);
            quickSort(nums, p + 1, right);
        }
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int j = left;

        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                j++;
                swap(nums, i, j);
            }
        }
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
