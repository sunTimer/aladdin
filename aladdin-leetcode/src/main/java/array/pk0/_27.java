package array.pk0;

import org.junit.Test;

public class _27 {

    public int removeElement(int[] nums, int val) {
        int slow = -1;
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (slow != -1) {
                    nums[slow] = nums[i];
                    slow++;
                }
                size++;
            } else {
                if (slow == -1) {
                    slow = i;
                }
            }
        }
        return size;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 3, 4, 5, 6};
        removeElement(nums, 3);
    }
}
