package array.pk0;

import org.junit.Test;

public class _35 {

    public int searchInsert(int[] nums, int target) {
        int index = 0;
        for (int num : nums) {
            if (target > num) {
                index++;
            } else if (target == num) {
                return index;
            }
        }
        return index;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 4, 5};
        System.out.println(searchInsert(nums, 6));
    }
}
