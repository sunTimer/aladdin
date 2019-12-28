package array.pk1;

import org.junit.Assert;
import org.junit.Test;

public class _169 {

    public int majorityElement(int[] nums) {

        int count = 1;
        int base = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (base == nums[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                base = nums[++i];
                count = 1;
            }
        }
        return base;
    }

    @Test
    public void test() {
        int[] nums = {1, 3, 4, 4, 11, 4};
        int major = majorityElement(nums);
        Assert.assertEquals(4, major);

        int[] nums2 = {1, 23, 3, 3, 31, 1, 3};
        Assert.assertEquals(3, majorityElement(nums2));
    }
}
