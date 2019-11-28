package array;

import org.junit.Assert;
import org.junit.Test;

public class _53 {

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = nums[0];
        for (int num : nums) {
            if (sum >= 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(sum, ans);
        }
        return ans;
    }

    @Test
    public void test() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assert.assertEquals(6, maxSubArray(nums));

        int[] nums2 = {-2, -1};
        Assert.assertEquals(-1, maxSubArray(nums2));

        int[] nums3 = {8, -19, 5, -4, 20};
        Assert.assertEquals(21, maxSubArray(nums3));

    }
}
