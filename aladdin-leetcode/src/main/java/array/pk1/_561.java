package array.pk1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class _561 {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            sum += nums[i];
        }
        return sum;
    }

    @Test
    public void test() {
        int[] nums = {1, 4, 3, 2};
        Assert.assertEquals(4, arrayPairSum(nums));
    }
}
