package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shixu
 */
public class QuickSort {

    public void quickSort(List<Integer> nums) {
        nums.add(Integer.MAX_VALUE);
        quickSort(nums, 0, nums.size() - 1);
    }

    public void quickSort(List<Integer> nums, int left, int right) {
        if (left < right) {
            int j = partition(nums, left, right);
            quickSort(nums, left, j);
            quickSort(nums, j + 1, right);
        }
    }

    private int partition(List<Integer> nums, int left, int right) {
        int i = left;
        int j = right;

        int pivot = nums.get(left);
        while (i < j) {
            do {
                i++;
            } while (nums.get(i) <= pivot);

            do {
                j--;
            } while (nums.get(j) > pivot);

            if (i < j) {
                swap(nums, j, i);
            }
        }
        swap(nums, left, j);
        return j;
    }

    private void swap(List<Integer> nums, int left, int right) {
        int tmp = nums.get(left);
        nums.set(left, nums.get(right));
        nums.set(right, tmp);
    }

    @Test
    public void test() {
        List<Integer> nums = Arrays.asList(4, 2,4, 1, 5, 12, 3, 12, 52);
        nums = new ArrayList<>(nums);
        quickSort(nums);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }
}
