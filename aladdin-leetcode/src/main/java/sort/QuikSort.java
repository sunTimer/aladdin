package sort;

public class QuikSort {

    public void quickSort(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        int base = nums[right];

        while (left < right) {
            if (nums[left] > base) {
                swap(left, right, nums);
                right--;
                if (nums[right] > base) {
                    right--;
                } else {
                    swap(left, right, nums);
                }
            } else {

            }
        }
    }

    private void swap(int left, int right, int[] nums) {
    }
}
