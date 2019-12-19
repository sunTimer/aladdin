package array.pk1;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class _532 {

    // todo
    public int findPairs(int[] nums, int k) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            set.add(num);
        }

        Set<Integer> ret = new HashSet<>(nums.length);
        for (Integer num : set) {
            if (set.contains(num - k)) {
                ret.add(num << 1 - k);
            } else if (set.contains(num + k)) {
                ret.add(num << 1 + k);
            }
        }
        return ret.size();
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4, 5};
        Assert.assertEquals(4, findPairs(nums, 1));

        int[] nums2 = {3, 1, 4, 1, 5};
        Assert.assertEquals(2, findPairs(nums2, 2));

        int[] nums3 = {1, 3, 1, 5, 4,};
        Assert.assertEquals(4, findPairs(nums3, 0));
    }
}
