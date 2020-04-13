package dp;

import org.junit.Test;

public class _53 {

    public int maxSubArray(int[] nums) {

        int sum = nums[0];
        int currMax = sum;

        for (int i = 1; i < nums.length; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            currMax = Math.max(sum, currMax);
        }
        return currMax;
    }

    @Test
    public void test() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }
}
