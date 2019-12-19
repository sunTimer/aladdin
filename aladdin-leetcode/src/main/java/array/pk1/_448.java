package array.pk1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[Math.abs(nums[i]) - 1] = -Math.abs(Math.abs(nums[i])-1);
        }
        List<Integer> ret = new ArrayList<>();
        for (int num : nums) {
            if (num > 0){
                ret.add(num);
            }
        }
        return ret;
    }

    @Test
    public void test() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDisappearedNumbers(nums));
    }
}
