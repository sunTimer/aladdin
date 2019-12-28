package array.pk1;

import org.junit.Test;

import java.util.*;

public class _219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 1; i <= k; i++) {
            int l = 0;
            int r = l + i;
            while (r < nums.length) {
                if (nums[l++] == nums[r++]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexList = map.computeIfAbsent(nums[i], (v) -> new ArrayList<>());
            indexList.add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexList = map.get(nums[i]);
            for (Integer index : indexList) {
                if (index == i) {
                    continue;
                }
                if (Math.abs(index - i) <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        Set<Integer> set = new HashSet<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 1, 2, 3};
        System.out.println(containsNearbyDuplicate2(nums, 2));
    }
}

