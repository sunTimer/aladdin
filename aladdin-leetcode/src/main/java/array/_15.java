package array;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _15 {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }
        Map<Integer, Integer> numSizeMap = new HashMap<>();
        for (int num : nums) {
            if (numSizeMap.containsKey(num)) {
                numSizeMap.put(num, numSizeMap.get(num) + 1);
            } else {
                numSizeMap.put(num, 1);
            }
        }


        List<List<Integer>> ret = new ArrayList<>();
        for (int num : nums) {
            int twoSum = 0 - num;
            List<List<Integer>> twoSumPair = twoSum(nums, twoSum, numSizeMap);
            if (!twoSumPair.isEmpty()) {
                for (List<Integer> tmp : twoSumPair) {
                    tmp.add(num);
                    ret.add(tmp);
                }
            }
        }
        return ret;
    }

    public List<List<Integer>> twoSum(int[] nums, int twoSum, Map<Integer, Integer> numMap) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int a : nums) {
            int b = twoSum - a;
            if (numMap.containsKey(b) && numMap.get(a) > 0 && numMap.get(b) > 0) {
                numMap.put(a, numMap.get(a) - 1);
                numMap.put(b, numMap.get(b) - 1);
                numMap.put(-twoSum, numMap.get(-twoSum) - 1);
                List<Integer> pair = new ArrayList<>();
                pair.add(a);
                pair.add(b);
                ret.add(pair);
            }
        }
        return ret;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> indexs;
            if (numMap.containsKey(num)) {
                indexs = numMap.get(num);
            } else {
                indexs = new ArrayList<>();
                numMap.put(num, indexs);
            }
            indexs.add(i);
        }

        int[] two = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int need = target - num;
            if (numMap.containsKey(need)) {
                List<Integer> needIndex = numMap.get(need);
                List<Integer> numIndex = numMap.get(num);

                if (numIndex.size() > 0) {
                    int one = numIndex.remove(0);
                    if (needIndex.size() > 0) {
                        two[0] = one;
                        two[1] = needIndex.remove(0);
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }

            }
        }
        return two;
    }

    @Test
    public void test() {
        int[] nums = {0, 1, -1, 4};
        for (int i : twoSum(nums, 4)) {
            System.out.println(i);
        }
    }
}
