package array.pk0;

import org.junit.Assert;
import org.junit.Test;

public class _167 {
    public int[] twoSum(int[] numbers, int target) {
        int[] ret = new int[2];
        for (int i = 0; i < numbers.length - 1; i++) {
            int secondIndex = binSearch(numbers, i + 1, numbers.length - 1, target - numbers[i]);
            if (secondIndex == -1) {
                continue;
            }
            ret[0] = i + 1;
            ret[1] = secondIndex + 1;
            break;
        }
        return ret;
    }


    public int[] twoSum2(int[] numbers, int target) {

        return null;
    }

    @Test
    public void test() {
        int[] nums = {2, 7, 11, 15};
        int[] ret = twoSum(nums, 18);
        Assert.assertEquals(2, ret[0]);
        Assert.assertEquals(3, ret[1]);

        ret = twoSum(nums, 17);
        Assert.assertEquals(1, ret[0]);
        Assert.assertEquals(4, ret[1]);

        ret = twoSum(nums, 13);
        Assert.assertEquals(1, ret[0]);
        Assert.assertEquals(3, ret[1]);

        int[] nums2 = {1, 2, 3, 4, 4, 9, 56, 90};
        ret = twoSum(nums2, 8);
        Assert.assertEquals(4, ret[0]);
        Assert.assertEquals(5, ret[1]);


    }


    private int binSearch(int[] numbers, int left, int right, int target) {
        int mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            int midV = numbers[mid];
            if (target == midV) {
                return mid;
            }
            if (target > midV) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

