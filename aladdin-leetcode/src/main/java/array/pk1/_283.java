package array.pk1;

import org.junit.Assert;
import org.junit.Test;

public class _283 {
    public void moveZeroes(int[] nums) {
        int m = -1;
        int n = 0;
        while (n < nums.length) {
            if (nums[n] != 0) {
                if (m != -1) {
                    nums[m] = nums[n];
                    nums[n] = 0;
                    m = m + 1;
                }
            } else {
                if (m == -1) {
                    m = n;
                }
            }
            n++;
        }
    }

    @Test
    public void test() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println();
    }
}
