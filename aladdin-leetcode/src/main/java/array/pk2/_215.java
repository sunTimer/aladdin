package array.pk2;


import org.junit.Test;

public class _215 {

    @Test
    public void test() {
        int[] nums = {4, 3, 1, 5, 63, 2};
        int i = findKthLargest(nums, 4);
        System.out.println(i);
    }

    public int findKthLargest(int[] nums, int k) {
        return help(0, nums.length - 1, nums, nums.length - k);
    }


    public int help(int left, int right, int[] nums, int k) {
        int p = partition(left, right, nums);
        if (p == k) {
            return nums[p];
        }
        if (p < k) {
            return nums[partition(p + 1, right, nums)];
        }
        return nums[partition(left, p - 1, nums)];
    }

    public int partition(int left, int right, int[] nums) {
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

    @Test
    public void testPartition() {
        int[] nums = {2,3,1};
        int partition = partition(0, nums.length - 1, nums);
        System.out.println(partition);
    }

    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
